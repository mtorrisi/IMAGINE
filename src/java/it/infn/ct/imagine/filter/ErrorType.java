/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.infn.ct.imagine.filter;

/**
 *
 * @author mario
 */

enum ErrorType {
    OUT_OF_BOUND_TIMESTAMP(1), 
    INVALID_API_KEY(2), 
    WRONG_SIGNATURE(3),
    DISABLED_API_KEY(4);
    
    private final int value;
    
    ErrorType(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
}
