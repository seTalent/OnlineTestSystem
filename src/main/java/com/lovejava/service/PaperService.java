package com.lovejava.service;

import com.lovejava.pojo.Paper;

public interface PaperService {
    int insertOnePaper(Paper paper);

    int updatePaperByMajor(Paper paper);

    Paper selectPaperByMajor(String major);
}
