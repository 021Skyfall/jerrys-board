$(document).ready(function() {
    // 검색 옵션 변경 시 UI 처리
    $('#searchOptions').on('change', function() {
        $('select[id^="o1"], input[id^="o"]').hide().val('');

        const selectedOption = $(this).val();
        if (selectedOption === '6') {
            $('#o6a, #o6b').show();
        } else if (selectedOption !== '0') {
            $('#o' + selectedOption).show();
        }
    });

    // 검색 버튼 클릭 이벤트
    $('#searchBtn').on('click', function() {
        searchAndRender(1);
    });

    // 전체 선택/해제 체크박스
    $('#chkAll').on('click', function() {
        $('.chk').prop('checked', this.checked);
    });

    // 선택 삭제 버튼 클릭 이벤트
    $('#deleteAllBtn').on('click', function() {
        const checkArr = $('.chk:checked').map(function() {
            return $(this).val();
        }).get();

        if (checkArr.length === 0) {
            alert("선택된 항목이 없습니다.");
            return;
        }

        if (confirm("선택된 글을 전부 삭제하시겠습니까?")) {
            $.ajax({
                url: CONTEXT_PATH + "/freeBoardDeleteAll",
                type: "post",
                traditional: true,
                data: { "arr": checkArr },
                success: function(response) {
                    alert("삭제 완료");
                    searchAndRender(1);
                },
                error: function(xhr, status, error) {
                    alert("에러 발생\n" + xhr.responseText);
                }
            });
        }
    });
});

// 검색 및 렌더링 함수
function searchAndRender(page) {
    const currentPage = page || 1;
    const selectedOption = $('#searchOptions').val();
    let st, startDate, endDate;
    let isValid = true;

    const regExpForNum = /^[0-9]+$/;
    const regExpForDate = /^(19|20)\d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$/;
    switch (selectedOption) {
        case '2':
            const checkNum = $('#o2').val();
            if (checkNum && !regExpForNum.test(checkNum)) {
                alert("글번호 검색은 공백 없이 숫자만 가능합니다.");
                isValid = false;
            } else { st = checkNum; }
            break;
        case '6':
            const startDateCheck = $('#o6a').val();
            const endDateCheck = $('#o6b').val();
            if ((startDateCheck && !regExpForDate.test(startDateCheck)) || (endDateCheck && !regExpForDate.test(endDateCheck))) {
                alert("날짜는 공백 없이 YYYYMMDD 형식만 가능합니다.\n예) 20250605");
                isValid = false;
            } else if (startDateCheck > endDateCheck) {
                alert("시작 날짜는 종료 날짜보다 클 수 없습니다.");
                isValid = false;
            } else {
                startDate = startDateCheck;
                endDate = endDateCheck;
            }
            break;
        default:
            st = $('#o' + selectedOption).val();
            break;
    }
    if (!isValid) return;

    $.ajax({
       url: CONTEXT_PATH + "/searchBoard",
       type: "post",
       data: JSON.stringify({
           curPage: currentPage,
           searchType: selectedOption,
           keyword: st,
           startDate: startDate,
           endDate: endDate
       }),
       contentType: "application/json; charset=UTF-8",
       success: function(response) {
            if (response.list.length === 0 && response.paging.curPage > 1) {
                searchAndRender(response.paging.totalPageCount);
            } else {
                renderTable(response.list);
                renderPagination(response.paging);
            }
       },
       error: function(xhr, status, error) {
            alert("에러 발생\n" + xhr.responseText);
       }
    });
}

// 테이블 렌더링 함수
function renderTable(list) {
    const tableBody = $('#tb');
    tableBody.empty();
    if (list && list.length > 0) {
        list.forEach(function(dto) {
            const newRow = `
                <tr>
                    <td><input type="checkbox" class="chk" value="${dto.num}"></td>
                    <td>${dto.codeType}</td>
                    <td>${dto.num}</td>
                    <td><a href="${CONTEXT_PATH}/freeBoardDetail?num=${dto.num}">${dto.title}</a></td>
                    <td>${dto.name}</td>
                    <td>${dto.regdate}</td>
                </tr>
            `;
            tableBody.append(newRow);
        });
    } else {
        tableBody.html('<tr><td colspan="6" align="center">검색 결과가 없습니다.</td></tr>');
    }
}

// 페이지네이션 렌더링 함수
function renderPagination(paging) {
    const pagination = $('#pagination-area');
    pagination.empty();

    if (!paging || paging.totalPageCount <= 1) return;

    let pageHtml = "";
    if (paging.curPage > 1) {
       pageHtml += `<a href="javascript:searchAndRender(1);">&laquo;</a> `;
       if(paging.curPage > paging.pageSize) {
            pageHtml += `<a href="javascript:searchAndRender(${paging.firstPage - 1});">&lt;</a> `;
       }
    }

    for (let i = paging.firstPage; i <= paging.lastPage; i++) {
       if (i === paging.curPage) {
          pageHtml += `<a href="#" class="active"><strong>${i}</strong></a> `;
       } else {
          pageHtml += `<a href="javascript:searchAndRender(${i});">${i}</a> `;
       }
    }

    if (paging.curPage < paging.totalPageCount) {
        if(paging.lastPage < paging.totalPageCount) {
           pageHtml += `<a href="javascript:searchAndRender(${paging.lastPage + 1});">&gt;</a> `;
        }
       pageHtml += `<a href="javascript:searchAndRender(${paging.totalPageCount});">&raquo;</a> `;
    }

    pagination.html(pageHtml);
}