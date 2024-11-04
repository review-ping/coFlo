package com.reviewping.coflo.domain.gitlab.controller;

import com.reviewping.coflo.domain.gitlab.controller.dto.request.GitlabSearchRequest;
import com.reviewping.coflo.domain.gitlab.controller.dto.response.GitlabProjectPageResponse;
import com.reviewping.coflo.domain.gitlab.service.GitlabApiService;
import com.reviewping.coflo.domain.user.entity.User;
import com.reviewping.coflo.global.auth.AuthUser;
import com.reviewping.coflo.global.common.response.ApiResponse;
import com.reviewping.coflo.global.common.response.impl.ApiSuccessResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gitlab")
@RequiredArgsConstructor
public class GitlabApiController {

    private final GitlabApiService gitlabApiService;

    @GetMapping("/search")
    public ApiResponse<GitlabProjectPageResponse> getGitlabProjects(
            @AuthUser User user,
            @RequestParam(name = "keyword", defaultValue = "") String keyword,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        GitlabProjectPageResponse gitlabProjects =
                gitlabApiService.getGitlabProjects(
                        user.getId(), new GitlabSearchRequest(keyword, page, size));
        return ApiSuccessResponse.success(gitlabProjects);
    }

    @GetMapping("/{gitlabProjectId}/branches")
    public ApiResponse<List<String>> getGitlabProjectBranched(
            @AuthUser User user, @PathVariable("gitlabProjectId") Long gitlabProjectId) {
        return ApiSuccessResponse.success(
                gitlabApiService.getGitlabProjectBranches(user.getId(), gitlabProjectId));
    }
}
