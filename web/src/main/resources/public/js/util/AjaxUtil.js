var FormAjaxHandler = function () {
}

FormAjaxHandler.send = function ($form, fnSuccess, fnError) {

    $.ajax({
        type: $form.attr('method'),
        url: $form.attr('action'),
        data: JSON.stringify($form.serializeObject()),
        contentType: "application/json; charset=UTF-8",
        success: function () {

            if (fnSuccess) {
                fnSuccess();
            }
        },
        error: function (request, status, error) {
            if (fnError) {
                fnError(request, status, error);
            }
        }

    });
}


FormAjaxHandler.sendResult = function ($form, fnSuccess, fnError) {
    $.ajax({
        type: $form.attr('method'),
        url: $form.attr('action'),
        data: JSON.stringify($form.serializeObject()),
        dataType: "json",
        contentType: "application/json; charset=UTF-8",
        success: function (result) {


            if (fnSuccess) {
                fnSuccess(result);
            }
        },
        error: function (request, status, error) {
            console.log(request , status , error);
            if (fnError) {
                fnError(request, status, error);
            }
        }

    });
}


var AjaxHandler = function () {
}


AjaxHandler.get = function (url, fnSuccess, fnError) {


    $.ajax({
        type: 'get',
        url: url,
        success: function () {
            if (fnSuccess) {
                fnSuccess();
            }
        },
        error: function (request, status, error) {
            if (fnError) {
                fnError(request, status, error);
            }
        }

    });
}

AjaxHandler.getResult = function (url, fnSuccess, fnError) {


    $.ajax({
        type: 'get',
        url: url,
        success: function (result) {
            if (fnSuccess) {
                fnSuccess(result);
            }
        },
        error: function (request, status, error) {
            if (fnError) {
                fnError(request, status, error);
            }
        }

    });
}

AjaxHandler.post = function (url, dataObj, fnSuccess, fnError) {


    $.ajax({
        type: 'post',
        url: url,
        data: JSON.stringify(dataObj),
        contentType: "application/json; charset=UTF-8",
        success: function () {
            if (fnSuccess) {
                fnSuccess();
            }
        },
        error: function (request, status, error) {
            if (fnError) {
                fnError(request, status, error);
            }
        }

    });


}


AjaxHandler.postResult = function (url, dataObj, fnSuccess, fnError) {


    $.ajax({
        type: 'post',
        url: url,
        data: JSON.stringify(dataObj),
        dataType: "json",
        contentType: "application/json; charset=UTF-8",
        success: function (result) {
            if (fnSuccess) {
                fnSuccess(result);
            }
        },
        error: function (request, status, error) {
            if (fnError) {
                fnError(request, status, error);
            }
        }

    });
}

AjaxHandler.postResultWithQueryParam = function (url, dataObj, fnSuccess, fnError) {


    $.ajax({
        type: 'post',
        url: url,
        data: dataObj,
        dataType: "json",
        contentType: "application/json; charset=UTF-8",
        success: function (result) {
            if (fnSuccess) {
                fnSuccess(result);
            }
        },
        error: function (request, status, error) {
            if (fnError) {
                console.log(request, status, error);
                fnError(request, status, error);
            }
        }

    });
}

AjaxHandler.delete = function (url, fnSuccess, fnError) {


    $.ajax({
        type: 'delete',
        url: url,
        contentType: "application/json; charset=UTF-8",
        success: function () {
            if (fnSuccess) {
                fnSuccess();
            }
        },
        error: function (request, status, error) {
            if (fnError) {
                fnError(request.responseJSON);
            }
        }
    });
}
