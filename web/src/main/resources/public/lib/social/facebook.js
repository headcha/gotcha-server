window.fbAsyncInit = function() {
    FB.init({
        appId      : '291147084566919',
        xfbml      : true,
        version    : 'v2.6'
    });
};

(function(d, s, id){
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) {return;}
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/ko_KR/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

function facebook() {

    this.getLoginStatus = function() {
        FB.getLoginStatus(function(response) {
            console.log('accessToken : ' + response.authResponse.accessToken);
            console.log('expiresIn : ' + response.authResponse.expiresIn);
            console.log('signedRequest : ' + response.authResponse.signedRequest);
            console.log('userID : ' + response.authResponse.userID);
        });
    };

    this.login = function() {
        FB.login(function(response) {
            console.log(response);
        }, {scope: 'public_profile,email,user_friends'});
    };

    this.logout = function() {
        FB.logout(function(response) {
            console.log(response);
        });
    };

    this.share = function() {
        FB.ui(
            {
                method: 'share',
                href: location.href
            }, function(response){});
    };

    this.shareOpenGraph = function() {
        FB.ui({
            method: 'share_open_graph',
            action_type: 'og.likes',
            action_properties: JSON.stringify({
                object: location.href
            })
        }, function(response){});
    };

}