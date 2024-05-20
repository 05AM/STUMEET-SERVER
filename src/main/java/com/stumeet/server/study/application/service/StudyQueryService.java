package com.stumeet.server.study.application.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.study.adapter.in.web.response.JoinedStudiesResponse;
import com.stumeet.server.study.adapter.in.web.response.StudyDetailResponse;
import com.stumeet.server.study.application.port.in.StudyQueryUseCase;
import com.stumeet.server.study.application.port.out.StudyQueryPort;
import com.stumeet.server.study.application.port.in.mapper.StudyUseCaseMapper;
import com.stumeet.server.study.domain.Study;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyQueryService implements StudyQueryUseCase {

	private final StudyQueryPort studyQueryPort;
	private final StudyUseCaseMapper studyUseCaseMapper;

	@Override
	public StudyDetailResponse getStudyDetailById(Long id) {
		Study study = studyQueryPort.getById(id);
		return studyUseCaseMapper.toStudyDetailResponse(study);
	}

	@Override
	public JoinedStudiesResponse getJoinedStudies(Long memberId) {
		List<Study> involvedStudies = studyQueryPort.getMemberRecentStudies(memberId);
		return studyUseCaseMapper.toMyStudiesResponse(involvedStudies);
	}
}
