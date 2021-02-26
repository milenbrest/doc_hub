package com.dochub.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachmentDTO
{
    private Long    id;
    private byte[ ] content;
    private String  name;
    private Long    visit;
}
