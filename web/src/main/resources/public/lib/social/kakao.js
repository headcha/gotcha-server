Kakao.init('fff14747687529bb995b68a5b6a08c7e');

function kakao() {

    this.login = function() {
        Kakao.Auth.login({
            success: function(authObj) {
                console.log(JSON.stringify(authObj));
            },
            fail: function(err) {
                alert(JSON.stringify(err));
                console.log(JSON.stringify(err));
            }
        });
    };

    this.logout = function() {
        Kakao.Auth.logout({
            success: function(authObj) {
                console.log(JSON.stringify(authObj));
            },
            fail: function(err) {
                alert(JSON.stringify(err));
                console.log(JSON.stringify(err));
            }
        });
    };

    this.getUserProfile = function() {
        Kakao.API.request({
            url: '/v1/user/me',
            success: function(res) {
                alert(JSON.stringify(res));
            },
            fail: function(error) {
                alert(JSON.stringify(error));
            }
        });
    };

    this.shareStory = function(text , url) {
        Kakao.Story.share({
            url: url,
            text: text
        });
    };

    this.sendLink = function(label, imgUrl, webText) {
        Kakao.Link.sendTalkLink({
            label: label,
            image: {
                src: imgUrl,
                width: '300',
                height: '200'
            },
            webLink: {
                text:webText,
                url: location.href
            },
            fail: function() {
                kakao.shareStory(label);
            }
        });
    }
}