var reviewManager = (function () {
    var getAll = function (obj, callback) {
        console.log("obj : "+obj);
        console.log("callbakc :"+callback);
        $.ajax({
            type: 'get',
            url: '/reviews/' + obj,
            dataType: 'json',
            success: callback
        });


    };

    var list =function (obj,callback) {
        $.ajax({
            type: 'post',
            url: '/shop/imgList',
            data: JSON.stringify(obj),
            contentType: "application/json",
            dataType: 'json',
            success: callback
        });
    }
    var add = function (obj, callback) {

        $.ajax({
            type: 'post',
            url: '/reviews/' + obj.productId,
            data: JSON.stringify(obj),
            contentType: "application/json",
            dataType: 'json',
            success: callback,
        })
            .fail(function () {
                alert("구매한 고객만 리뷰를 달 수 있습니다.");
            })

    };

    var update = function (obj, callback) {
        $.ajax({
            type: 'put',
            url: '/replies/' + obj.bno + "/" + obj.rno,
            data: JSON.stringify(obj),
            contentType: "application/json",
            dataType: 'json',
            success: callback
        });
    };

    var remove = function (obj, callback) {

        $.ajax({
            type: 'delete',
            url: '/replies/' + obj.bno + "/" + obj.rno,
            dataType: 'json',
            success: callback
        });

    };

    return {
        getAll: getAll,
        add: add,
        update: update,
        remove: remove,
        list:list
    }

})();
