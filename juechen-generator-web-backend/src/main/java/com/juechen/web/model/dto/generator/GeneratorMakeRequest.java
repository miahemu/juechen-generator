package com.juechen.web.model.dto.generator;

import com.juechen.maker.meta.Meta;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Juechen
 * @version : GeneratorMakeRequest.java
 */
@Data
public class GeneratorMakeRequest implements Serializable {

    private String zipFilePath;

    private Meta meta;

    private static final long serialVersionUID = 1L;
}
