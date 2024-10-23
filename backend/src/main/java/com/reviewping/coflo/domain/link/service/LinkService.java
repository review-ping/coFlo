package com.reviewping.coflo.domain.link.service;

import static com.reviewping.coflo.global.error.ErrorCode.USER_GITLAB_ACCOUNT_NOT_EXIST;
import static com.reviewping.coflo.global.error.ErrorCode.USER_NOT_EXIST;

import com.reviewping.coflo.domain.gitlab.dto.response.GitlabProjectDetailContent;
import com.reviewping.coflo.domain.gitlab.dto.response.GitlabProjectPageContent;
import com.reviewping.coflo.domain.gitlab.service.GitLabApiService;
import com.reviewping.coflo.domain.link.controller.dto.request.GitlabSearchRequest;
import com.reviewping.coflo.domain.link.controller.dto.response.GitlabProjectPageResponse;
import com.reviewping.coflo.domain.link.controller.dto.response.GitlabProjectResponse;
import com.reviewping.coflo.domain.project.repository.ProjectRepository;
import com.reviewping.coflo.domain.user.entity.GitlabAccount;
import com.reviewping.coflo.domain.user.repository.UserRepository;
import com.reviewping.coflo.domain.userproject.repository.UserProjectRepository;
import com.reviewping.coflo.global.error.exception.BusinessException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LinkService {

    private final GitLabApiService gitLabApiService;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final UserProjectRepository userProjectRepository;

    public GitlabProjectPageResponse getGitlabProjects(
            Long userId, GitlabSearchRequest gitlabSearchRequest) {
        GitlabAccount gitlabAccount = findGitlabAccount(userId);
        GitlabProjectPageContent gitlabProjectPageContent =
                gitLabApiService.searchGitlabProjects(
                        gitlabAccount.getDomain(),
                        gitlabAccount.getUserToken(),
                        gitlabSearchRequest);

        List<GitlabProjectResponse> gitlabProjectResponses =
                gitlabProjectPageContent.gitlabProjectDetailContents().stream()
                        .map(project -> createGitlabProjectResponse(project, gitlabAccount.getId()))
                        .toList();
        return GitlabProjectPageResponse.of(
                gitlabProjectResponses, gitlabProjectPageContent.pageDetail());
    }

    private GitlabAccount findGitlabAccount(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new BusinessException(USER_NOT_EXIST))
                .getGitlabAccounts()
                .stream()
                .findFirst()
                .orElseThrow(() -> new BusinessException(USER_GITLAB_ACCOUNT_NOT_EXIST));
    }

    private GitlabProjectResponse createGitlabProjectResponse(
            GitlabProjectDetailContent content, Long gitlabAccountId) {
        return projectRepository
                .findByGitlabProjectId(content.id())
                .map(
                        project -> {
                            boolean isLinked =
                                    userProjectRepository.existsByGitlabAccountIdAndProjectId(
                                            gitlabAccountId, project.getId());
                            return GitlabProjectResponse.of(content, true, isLinked);
                        })
                .orElseGet(() -> GitlabProjectResponse.of(content, false, false));
    }
}
