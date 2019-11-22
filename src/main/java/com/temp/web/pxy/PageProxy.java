package com.temp.web.pxy;

import lombok.Data;

@Data
public class PageProxy {
	private int page, pageSize, blockSize, rowCount;
	private int startRow, endRow, startPage, endPage, pageCount, blockCount; 
	private Boolean prevBlock,nextBlock; 
	public void paging() {
		pageSize = 5;
		rowCount = 100;
		blockCount = 5;
		pageCount = (rowCount-1)/pageSize +1;
		blockCount = (pageCount-1)/blockSize +1;
		startPage = (page-1)/5 + 1;
		endPage = startPage + blockSize -1 < pageCount ? startPage+ blockSize -1 : pageCount;
		startRow = (page -1) * pageSize;
		endRow = startRow + 4 < pageCount ? startRow + 4 : pageCount-1;
		prevBlock = startPage > 1;
		nextBlock = (endPage -1) / blockSize + 1< blockCount;
	}
}
