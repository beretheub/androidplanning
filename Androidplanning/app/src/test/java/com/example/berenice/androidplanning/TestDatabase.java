package com.example.berenice.androidplanning;

import android.content.Context;
import android.test.mock.MockContext;

import com.example.berenice.androidplanning.database.ImportHandler;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TestDatabase {
    @Test
    public void importStaffWorks(){
        Context context = new MockContext();
        ImportHandler ih = new ImportHandler(context);

        ih.importStaff(context, "J1");


    }
}
