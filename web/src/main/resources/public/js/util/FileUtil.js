var FileUtil = function () {
}

FileUtil.upload = function ($el , fnSuccess , fnError) {

    var $progress = FileUtil._createProgress();

    $el.fileupload({
        url: '/api/file/image',
        dataType: 'json',
        // Enable image resizing, except for Android and Opera,
        // which actually support image resizing, but fail to
        // send Blob objects via XHR requests:
        disableImageResize: /Android(?!.*Chrome)|Opera/
            .test(window.navigator && navigator.userAgent),
        imageMaxWidth: 1920,
        imageMaxHeight: 1080,
        imageCrop: false, // Force cropped images,
        send: function (e, data) {

            $(this).find('i').removeClass().addClass('fa fa-circle-o-notch fa-spin');
            /*$(this).parent().next().after($progress);*/
            return true;
        },
        progress: function (e, data) {
            /*var progress = parseInt(data.loaded / data.total * 100, 10);
            $progress.find('.progress-bar').css(
                'width',
                progress + '%'
            );*/
        },
        done: function (e, data) {
            /*$progress.find('.progress-bar').css(
                'width',
                0 + '%'
            );
            $progress.remove();*/
            $(this).find('i').removeClass().addClass('fa fa-file pl-10');
            if (fnSuccess)
                fnSuccess(data , $(this));
        },
        fail : function() {
            /*$progress.find('.progress-bar').css(
                'width',
                0 + '%'
            );

            $progress.remove();*/
            if (fnError)
                fnError();
        }
    }).prop('disabled', !$.support.fileInput)
        .parent().addClass($.support.fileInput ? undefined : 'disabled');



}


FileUtil._createProgress = function() {
    var progress = $('<div>' , {
        'class' : 'progress'});

    var progressBar = $('<div>' , {
        'class' : 'progress-bar progress-bar-success'
    });

    return progress.append(progressBar);
}
