package biweekly.property;

import java.util.LinkedHashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import biweekly.util.XmlUtils;

/*
 Copyright (c) 2013-2015, Michael Angstadt
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met: 

 1. Redistributions of source code must retain the above copyright notice, this
 list of conditions and the following disclaimer. 
 2. Redistributions in binary form must reproduce the above copyright notice,
 this list of conditions and the following disclaimer in the documentation
 and/or other materials provided with the distribution. 

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * Stores a property that was parsed from an xCal document (XML-encoded
 * iCalendar object) whose XML namespace was not part of the xCal XML namespace.
 * @author Michael Angstadt
 * @see <a href="http://tools.ietf.org/html/rfc6321#page-17">RFC 6321 p.17-8</a>
 */
public class Xml extends ValuedProperty<Document> {
	/**
	 * Creates an XML property.
	 * @param xml the XML to use as the property's value
	 * @throws SAXException if the XML cannot be parsed
	 */
	public Xml(String xml) throws SAXException {
		super(XmlUtils.toDocument(xml));
	}

	/**
	 * Creates an XML property.
	 * @param element the XML element to use as the property's value (the
	 * element is imported into an empty {@link Document} object)
	 */
	public Xml(Element element) {
		super(XmlUtils.createDocument());
		Node imported = value.importNode(element, true);
		value.appendChild(imported);
	}

	/**
	 * Creates an XML property.
	 * @param document the XML document to use as the property's value
	 */
	public Xml(Document document) {
		super(document);
	}

	/**
	 * Copy constructor.
	 * @param original the property to make a copy of
	 */
	public Xml(Xml original) {
		super(original);
		if (original.value != null) {
			value = XmlUtils.createDocument();
			Node node = value.importNode(original.value.getDocumentElement(), true);
			value.appendChild(node);
		}
	}

	@Override
	protected Map<String, Object> toStringValues() {
		Map<String, Object> values = new LinkedHashMap<String, Object>();
		values.put("value", (value == null) ? "null" : XmlUtils.toString(value));
		return values;
	}

	@Override
	public Xml copy() {
		return new Xml(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((value == null) ? 0 : XmlUtils.toString(value).hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Xml other = (Xml) obj;
		if (value == null) {
			if (other.value != null) return false;
		} else {
			if (other.value == null) return false;
			if (!XmlUtils.toString(value).equals(XmlUtils.toString(other.value))) return false;
		}
		return true;
	}
}
