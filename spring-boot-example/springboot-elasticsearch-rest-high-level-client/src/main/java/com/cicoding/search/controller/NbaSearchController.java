package com.cicoding.search.controller;

import com.cicoding.search.model.NbaPlayer;
import com.cicoding.search.service.NbaPlayerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author www.cicoding.cn
 */
@RestController
@RequestMapping("nba")
public class NbaSearchController {
    @Autowired
    private NbaPlayerService nbaPlayerService;

    @RequestMapping("importAll")
    public String importAll() throws IOException {
        nbaPlayerService.importAll();
        return "success";
    }

    @RequestMapping("searchMatch")
    public List<NbaPlayer> searchMatch(@RequestParam(value = "displayNameEn", required = false) String displayNameEn) throws IOException {
        return nbaPlayerService.searchMatch("displayNameEn", displayNameEn);
    }
    @RequestMapping("searchTerm")
    public List<NbaPlayer> searchTerm(@RequestParam(value = "country", required = false) String country
            , @RequestParam(value = "teamName", required = false) String teamName) throws IOException {
        return StringUtils.isNoneBlank(country)?nbaPlayerService.searchTerm("country", country):nbaPlayerService.searchTerm("teamName", teamName);
    }
    @RequestMapping("searchMatchPrefix")
    public List<NbaPlayer> searchMatchPrefix(@RequestParam(value = "displayNameEn", required = false) String displayNameEn) throws IOException {
        return nbaPlayerService.searchMatchPrefix("displayNameEn", displayNameEn);
    }
}
