package test.rpc.ifs.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import test.rpc.ifs.Echo;

public class EchoImpl implements Echo {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public String echo(String text) {
		return "hello " + text;
	}

	@Override
	public String date() {
		return sdf.format(new Date());
	}

}
