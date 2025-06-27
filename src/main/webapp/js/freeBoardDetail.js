$(document).ready(function() {
    $('#modifyBtn').on('click', function() {
        const data = {
            "codeType" : $('#selCodeType').val(),
            "num" : $('#pkNum').val(),
            "title" : $('#title').val(),
            "content" : $('#content').val()
        };

        if (confirm("정말 수정하시겠습니까?")) {
            $.ajax({
                url: CONTEXT_PATH + "/freeBoardModify",
                type: "post",
                data: JSON.stringify(data),
                contentType: "application/json; charset=UTF-8",
                success: function(response) {
                    if(confirm("수정 완료\n목록으로 돌아가시겠습니까?")) {
                        location.href = CONTEXT_PATH + "/freeBoardMain";
                    }
                },
                error: function(xhr, status, error) {
                    alert("에러 발생\n" + xhr.responseText);
                }
            });
        }
    });

    $('#deleteBtn').on('click', function() {
        const id = $('#pkNum').val();

        if (confirm("정말 삭제하시겠습니까?")) {
            $.ajax({
                url: CONTEXT_PATH + "/freeBoardDelete?num=" + id,
                type: "post",
                contentType: "application/json; charset=UTF-8",
                success: function(response) {
                    alert("삭제 완료");
                    location.replace(CONTEXT_PATH + "/freeBoardMain");
                },
                error: function(xhr, status, error) {
                    alert("에러 발생\n" + xhr.responseText);
                }
            });
        }
    });
});