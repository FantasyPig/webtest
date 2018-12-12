package com.alex.webtest.exception;

import com.alex.webtest.result.CodeMsg;

public class GlobalException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private CodeMsg cm;

    public GlobalException(CodeMsg cm){
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm(){
        return cm;
    }
}
