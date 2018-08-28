var NotificationUtil = function () {

}

NotificationUtil.showTop = function (message , type ,  delay) {
    $.notify({
        // options
        message: message
    },{
        // settings
        type: type || 'success',
        delay: delay || 2000,
        newest_on_top: true,
        placement: {
            from: "top",
            align: "right"
        }
    });
}