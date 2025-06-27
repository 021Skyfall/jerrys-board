package jerry.web.freeBoard.pagination;

// https://brilliantdevelop.tistory.com/65 참고
public class Paging {
	private int curPage = 1;          
	private int rowSizePerPage = 5;  
	private int pageSize = 5;       
	private int totalRowCount ;    
	
	private int firstRow ;      
	private int lastRow;          
	private int totalPageCount;   
	private int firstPage; 	     
	private int lastPage;
	
	// 검색 필드
	private String searchType;
	private String keyword;
	private String startDate;
	private String endDate;
	
	//page계산
	public void pageSetting() {
		totalPageCount = (totalRowCount  - 1) / rowSizePerPage + 1; 
		
		firstRow = (curPage - 1) * rowSizePerPage + 1;  
		
		lastRow = firstRow + rowSizePerPage - 1;    
		
		if(lastRow>totalRowCount) lastRow = totalRowCount;
		
		firstPage = (curPage - 1) / pageSize * pageSize + 1;
		
		lastPage = firstPage + pageSize - 1;
		
		if(lastPage > totalPageCount) lastPage = totalPageCount;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getRowSizePerPage() {
		return rowSizePerPage;
	}

	public void setRowSizePerPage(int rowSizePerPage) {
		this.rowSizePerPage = rowSizePerPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	public int getLastRow() {
		return lastRow;
	}

	public void setLastRow(int lastRow) {
		this.lastRow = lastRow;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
