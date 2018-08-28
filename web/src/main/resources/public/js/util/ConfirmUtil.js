var ConfirmUtil = function () {
}

ConfirmUtil.confirm = function ($context , elementSelector , fnConfirm) {

    $($context).on('click', elementSelector, function () {
        var $this = $(this);
        bootbox.confirm({
            size : 'small',
            message: "삭제 하시겠습니까?",
            buttons: {
                cancel: {
                    label: '취소'
                },
                confirm: {
                    label: '삭제',
                    className: 'btn-danger'
                }
            },
            callback: function (result) {
                if (result) {
                    fnConfirm($this);
                }
            }
        });
    });
}

ConfirmUtil.modalConfirm = function (message , fnConfirm) {
    bootbox.confirm({
        message: message,
        buttons: {
            cancel: {
                label: '취소'
            },
            confirm: {
                label: '삭제',
                className: 'btn-danger'
            }
        },
        callback: function (result) {
            if (result) {
                fnConfirm();
            }
        }
    });
}

