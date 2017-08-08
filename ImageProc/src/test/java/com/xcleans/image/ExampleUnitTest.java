package com.xcleans.image;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);

        IMOperation operation=new IMOperation();
        operation.addImage("");
        operation.rotate();
        operation.addImage();

        ConvertCmd convertCmd=new ConvertCmd();
//        convertCmd.setSearchPath();
        convertCmd.run(operation);
    }
}