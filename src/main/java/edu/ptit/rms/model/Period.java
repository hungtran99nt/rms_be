package edu.ptit.rms.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class Period {
    private Date beginDate;
    private Date endDate;
}
