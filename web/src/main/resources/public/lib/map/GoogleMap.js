var GoogleMap = function(id , latitude , longitude , zoom) {
    this.map = initMap(id , latitude , longitude);
    this.geocoder = new google.maps.Geocoder;
    this.marker = initMarker(this.map , latitude , longitude);
    this.infowindow = new google.maps.InfoWindow;

    function initMap(id , latitude , longitude) {

        var map = new google.maps.Map(document.getElementById(id) , {
            zoom: zoom,
            center: {lat: latitude, lng: longitude}
        });

        return map;
    }

    function initMarker(map , latitude , longitude) {

        var marker = new google.maps.Marker({
            map: map,
            draggable: true,
            animation: google.maps.Animation.DROP,
            position: new google.maps.LatLng(latitude, longitude)
        });

        return marker;
    }
};

GoogleMap.prototype.getMap = function () {
    return this.map;
}

GoogleMap.prototype.showAddressToMarker = function (latLng , fnSuccess) {
    var that = this;
    this.geocoder.geocode({'location': latLng}, function(results, status) {

        if (status === 'OK') {
            if (results[0]) {
                that.infowindow.setContent(results[0].formatted_address);
                that.infowindow.open(that.map, that.marker);
            } else {
                window.alert('검색 결과가 없습니다.');
            }

            if (fnSuccess)
                fnSuccess(results);

        } else {
            console.log(status);
            window.alert('오류가 발생했습니다. 잠시 후 다시 이용해 주세요');
        }
    });
}

GoogleMap.getCountry = function (geocodeResults) {
    for (var i=0; i < geocodeResults[0].address_components.length; i++) {
        for (var j=0; j < geocodeResults[0].address_components[i].types.length; j++) {
            if (geocodeResults[0].address_components[i].types[j] == "country") {
                var country = geocodeResults[0].address_components[i];
                return country.long_name;
            }
        }
    }

    return "";
};

GoogleMap.getLatitude = function (geocodeResults) {
    var geoLocation = geocodeResults[0].geometry.location;
    return geoLocation.lat();
};

GoogleMap.getLongitude = function (geocodeResults) {
    var geoLocation = geocodeResults[0].geometry.location;
    return geoLocation.lng();
};

GoogleMap.getCity = function (geocodeResults) {
    console.log(geocodeResults);
    for (var i=0; i < geocodeResults[0].address_components.length; i++) {
        for (var j=0; j < geocodeResults[0].address_components[i].types.length; j++) {
            if (geocodeResults[0].address_components[i].types[j] == "administrative_area_level_1" || geocodeResults[0].address_components[i].types[j] == "locality") {
                var city = geocodeResults[0].address_components[i];
                return city.long_name;
            }
        }
    }

    return "";
};

GoogleMap.getAddress = function (geocodeResults) {
    return geocodeResults[0].formatted_address;
};

GoogleMap.prototype.addEventListenerMapClick = function (fnSuccess) {
    var that = this;
    google.maps.event.addListener(this.map, 'click', function (mapPosition) {
        that.marker.setPosition(mapPosition.latLng);

        if (fnSuccess) {
            fnSuccess(mapPosition.latLng);
        }
    });
};

GoogleMap.prototype.addEventListenerMarkerDrag = function (fnSuccess) {

    google.maps.event.addListener(this.marker, 'dragend', function (markerPosition) {

        if (fnSuccess) {
            fnSuccess(markerPosition.latLng);
        }
    });
};

GoogleMap.prototype.geocodeAddress = function(address , fnSuccess , fnFail) {
    var that = this;
    this.geocoder.geocode({'address': address}, function(results, status) {
        if (status === google.maps.GeocoderStatus.OK) {
            var geoLocation = results[0].geometry.location;

            that.map.setCenter(geoLocation);

            that.marker.setPosition(geoLocation);
            that.infowindow.setContent(results[0].formatted_address);
            that.infowindow.open(that.map, that.marker);
            fnSuccess(results);
        } else {
            fnFail(status);
        }
    });
};

function initMap(id, latitude, longitude, zoom) {

    var map = new google.maps.Map(document.getElementById(id), {
        zoom: zoom,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        center: {lat: latitude, lng: longitude},
        draggable: false,
        scrollwheel: false
    });

    google.maps.event.addDomListener(window, "resize", function () {
        map.setCenter(this.latlng);
    });

    var marker = new google.maps.Marker({
        map: map,
        draggable: false,
        animation: google.maps.Animation.DROP,
        position: new google.maps.LatLng(latitude, longitude)
    });
}