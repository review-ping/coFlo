package com.reviewping.coflo.global.client.gitlab;

import com.reviewping.coflo.domain.gitlab.controller.dto.request.GitlabSearchRequest;

public class GitLabApiUrlBuilder {

    private static final String URL_PROTOCOL_HTTPS = "https://";

    private static final String USERINFO_ENDPOINT = "/api/v4/user";
    private static final String SINGLE_PROJECT_ENDPOINT = "/api/v4/projects/";
    private static final String PROJECT_SEARCH_ENDPOINT =
            "/api/v4/projects?order_by=updated_at&sort=desc";

    public static String createSearchGitlabProjectUrl(
            String gitlabUrl, GitlabSearchRequest gitlabSearchRequest) {
        return URL_PROTOCOL_HTTPS
                + gitlabUrl
                + PROJECT_SEARCH_ENDPOINT
                + "&search="
                + gitlabSearchRequest.keyword()
                + "&page="
                + gitlabSearchRequest.page()
                + "&per_page="
                + gitlabSearchRequest.size();
    }

    public static String createMRDiffsUrl(String gitlabUrl, Long gitlabProjectId, Long iid) {
        return URL_PROTOCOL_HTTPS
                + gitlabUrl
                + SINGLE_PROJECT_ENDPOINT
                + gitlabProjectId
                + "/merge_requests/"
                + iid
                + "/diffs";
    }

    public static String createNoteToMRUrl(String gitlabUrl, Long gitlabProjectId, Long iid) {
        return URL_PROTOCOL_HTTPS
                + gitlabUrl
                + SINGLE_PROJECT_ENDPOINT
                + gitlabProjectId
                + "/merge_requests/"
                + iid
                + "/notes";
    }

    public static String createSearchMergeRequestUrl(
            String gitlabUrl,
            Long gitlabProjectId,
            String mergeRequestState,
            GitlabSearchRequest gitlabSearchRequest) {
        return URL_PROTOCOL_HTTPS
                + gitlabUrl
                + SINGLE_PROJECT_ENDPOINT
                + gitlabProjectId
                + "/merge_requests"
                + "?state="
                + mergeRequestState
                + "&search="
                + gitlabSearchRequest.keyword()
                + "&page="
                + gitlabSearchRequest.page()
                + "&per_page="
                + gitlabSearchRequest.size();
    }

    public static String createSingleProjectUrl(String gitlabUrl, Long gitlabProjectId) {
        return URL_PROTOCOL_HTTPS + gitlabUrl + SINGLE_PROJECT_ENDPOINT + gitlabProjectId;
    }

    public static String createUserInfoUrl(String gitlabUrl) {
        return URL_PROTOCOL_HTTPS + gitlabUrl + USERINFO_ENDPOINT;
    }
}
