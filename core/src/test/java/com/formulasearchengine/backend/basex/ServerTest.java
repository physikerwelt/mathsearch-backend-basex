package com.formulasearchengine.backend.basex;

import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class ServerTest  {
	private Server srv;

	public ServerTest () throws IOException {
		srv = new Server();
	}

	static public String getFileContents (String fname) throws IOException {
		try (InputStream is = ServerTest.class.getClassLoader().getResourceAsStream(fname)) {
			final Scanner s = new Scanner(is, "UTF-8");
			//Stupid scanner tricks to read the entire file as one token
			s.useDelimiter("\\A");
			return s.hasNext() ? s.next() : "";
		}
	}
	@After
	public void shutDown() throws IOException {
		srv.shutdown();
	}
	@Test
	public void testImportData () throws Exception {
		final String file = this.getClass().getClassLoader().getResource( "sampleHarvest.xml" ).getPath();
		srv.importData( file );
		System.out.println(file);

	}
	//Depends on testImportData
	@Test
	public void testQuery () throws Exception {
		String fcontent = getFileContents( "sampleHarvest.xml" );
		srv.importData( fcontent );

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		srv.runQuery( "count(./*/*)", ps );
		assertEquals("104",baos.toString("UTF-8"));
		baos = new ByteArrayOutputStream();
		srv.runQuery( "info", ps );
		System.out.println(baos.toString());
	}

}