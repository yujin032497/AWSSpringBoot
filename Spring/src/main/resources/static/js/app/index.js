var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });
    },
    save : function() {
        console.log("저장");
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() { // 글 등록이 성공하면 메인페이지로 이동
            alert('글이 등록되었습니다.');
            window.location.href='/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        console.log("수정");
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        val id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href='/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();