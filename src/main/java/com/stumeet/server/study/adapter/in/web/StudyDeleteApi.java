package com.stumeet.server.study.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;
import com.stumeet.server.study.application.port.in.StudyDeleteUseCase;

import lombok.RequiredArgsConstructor;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/api/v1/studies")
public class StudyDeleteApi {

	private final StudyDeleteUseCase studyDeleteUseCase;

	@DeleteMapping("/{studyId}")
	public ResponseEntity<ApiResponse<Void>> delete(
		@AuthenticationPrincipal LoginMember member,
		@PathVariable Long studyId
	) {
		studyDeleteUseCase.delete(studyId, member.getMember().getId());

		return new ResponseEntity<>(
			ApiResponse.success(SuccessCode.DELETE_SUCCESS),
			HttpStatus.OK);
	}
}