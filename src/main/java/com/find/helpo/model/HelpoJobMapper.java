package com.find.helpo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
@NoArgsConstructor
public class HelpoJobMapper {

    private ModelMapper modelMapper;

    public HelpoJobDTO map(HelpoJob helpoJob) {
        return modelMapper.map(helpoJob, HelpoJobDTO.class);
    }

    public HelpoJob map(HelpoJobDTO helpoJobDTO) {
        return modelMapper.map(helpoJobDTO, HelpoJob.class);
    }
}
