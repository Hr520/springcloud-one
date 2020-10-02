package com.web.xss;


import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
	private final static Whitelist WHITELIST = Whitelist.relaxed();
	 
	private final static Document.OutputSettings OUTPUTSETTINGS = new Document.OutputSettings().prettyPrint( false );
 
	static {
		WHITELIST.addTags( "embed", "object", "param", "span", "div", "img" );
		WHITELIST.addAttributes( ":all", "style", "class", "id", "name" );
		WHITELIST.addAttributes( "object", "width", "height", "classid", "codebase" );
		WHITELIST.addAttributes( "param", "name", "value" );
		WHITELIST.addAttributes( "embed", "src", "quality", "width", "height", "allowFullScreen",
				"allowScriptAccess", "flashvars", "name", "type", "pluginspage" );
	}
 
	public XssHttpServletRequestWrapper(HttpServletRequest servletRequest ) {
		super( servletRequest );
	}
 
	@Override
	public String[] getParameterValues( String parameter ) {
		String[] values = super.getParameterValues( parameter );
		if( null == values ) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[ count ];
		for( int i = 0; i < count; i++ ) {
			encodedValues[ i ] = filterValue( values[ i ] );
		}
		return encodedValues;
	}
 
	@Override
	public String getParameter( String parameter ) {
		String value = super.getParameter( parameter );
		if (value!=null){
			value = filterValue(value);
		}
		return  value;
	}
 
	@Override
	public String getHeader( String name ) {
		String value = super.getHeader( name );
		if (value!=null){
			value = filterValue(value);
		}
		return  value;
	}
 
	private String filterValue( String s ) {
		StringBuilder sb = new StringBuilder(s.length());
		for (int i = 0, c = s.length(); i < c; i++) {
			char ch = s.charAt(i);
			switch (ch) {
				case '&': sb.append("&amp;");
					break;
				case '<': sb.append("&lt;");
					break;
				case '>': sb.append("&gt;");
					break;
				case '"': sb.append("&quot;");
					break;
				case '\'': sb.append("&#x27;");
					break;
				case '/': sb.append("&#x2F;");
					break;
				default: sb.append(ch);
			}
		}
		return sb.toString();
	}

}
