$(document).ready(function() {
    $('#submitBtn').on('click', function() {
        const codeType = $("select[name=codeType] option:selected").val();
        const name = $('#name').val();
        const title = $('#title').val();
        const content = $('#content').val();

        const data = {
            "codeType" : codeType,
            "name" : name,
            "title" : title,
            "content" : content
        };

        if (confirm("글을 등록하시겠습니까?")) {
            if (name === "") {
                alert("이름은 필수 항목입니다.");
            } else if (title === "") {
                alert("제목은 필수 항목입니다.");
            } else if (content === "") {
                alert("내용은 필수 항목입니다.");
            } else {
                $.ajax({
                    url: CONTEXT_PATH + "/freeBoardInsertPro",
                    type: "post",
                    data: JSON.stringify(data),
                    contentType: "application/json; charset=UTF-8",
                    dataType: "json",
                    success: function(response) {
                        if (confirm("작성 완료\n메인 화면으로 이동하시겠습니까?")) {
                            location.href = CONTEXT_PATH + "/freeBoardMain";
                        } else {
                            location.href = CONTEXT_PATH + "/freeBoardDetail?num=" + response.num;
                        }
                    },
                    error: function(xhr, status, error) {
                        alert("에러 발생\n" + xhr.responseText);
                    }
                });
            }
        }
    });
});