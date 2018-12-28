package com.Exception;


import org.springframework.stereotype.Service;

@Service
public class MyException extends Exception {

    public void reSameName() throws Exception {
        throw new Exception("Can't take the same name");
    }

    public void noEnoughMenory() throws Exception{
        throw new Exception("Not enough memory");
    }

    public void noContiMenroy() throws Exception{
        throw new Exception("Unable to allocate continuous space");
    }

    public void noDoc() throws Exception{
        throw new Exception("No such file");
    }

    public void noAnyDoc() throws Exception{
        throw new Exception("No files are stored");
    }
}
