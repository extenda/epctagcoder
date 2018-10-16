package org.epctagcoder.option.SGTIN;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.epctagcoder.parse.SGTIN.ParseSGTIN;
import org.epctagcoder.result.SGTIN;
import org.junit.Test;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.checkdigit.CheckDigitException;
import org.apache.commons.validator.routines.checkdigit.EAN13CheckDigit;

/**
 * Test output from conversion
 */
public class ConvertterTest {

	/**
	 * Test SGTIN conversion
	 * 
	 * @throws CheckDigitException
	 */
	@Test
	public void testSGTINConversion() throws CheckDigitException {

		final int GTIM_WO_CD_LEN = 13;

		Map<String, String> gtinData = new LinkedHashMap<>();
		gtinData.put("301B5BEB81525B199C82CC00", "08805583464766");
		gtinData.put("303ACB2FC2FD9BD99C82CD9E", "07323517839837");
		gtinData.put("303ACA40009D899BF2CE2943", "07313921613185");
		gtinData.put("303ACA40000E6B5BF1303F25", "07313920147650");
		gtinData.put("303ACA40000E6B5BF1303F1D", "07313920147650");
		gtinData.put("303ACA40000E6B5BF1303F22", "07313920147650");
		gtinData.put("303ACA40000E6B5BF1303F1E", "07313920147650");

		for (Map.Entry<String, String> entry : gtinData.entrySet()) {
			ParseSGTIN parseSGTIN = ParseSGTIN.Builder().withRFIDTag(entry.getKey()).build();
			SGTIN sgtin = parseSGTIN.getSGTIN();

			// Concatenate company prefix with item reference. Zeropad to 13 chars and add
			// check digit.
			String actual = StringUtils.leftPad(sgtin.getCompanyPrefix() + sgtin.getItemReference(), GTIM_WO_CD_LEN, '0');
			actual = actual + new EAN13CheckDigit().calculate(actual);

			assertEquals(entry.getValue(), actual);
		}

		Map<String, String> serailData = new LinkedHashMap<>();
		serailData.put("301B5BEB81525B199C82CC00", "110000000000");
		serailData.put("303ACB2FC2FD9BD99C82CD9E", "110000000414");
		serailData.put("303ACA40009D899BF2CE2943", "120037714243");
		serailData.put("303ACA40000E6B5BF1303F25", "120010587941");
		serailData.put("303ACA40000E6B5BF1303F1D", "120010587933");
		serailData.put("303ACA40000E6B5BF1303F22", "120010587938");
		serailData.put("303ACA40000E6B5BF1303F1E", "120010587934");

		for (Map.Entry<String, String> entry : serailData.entrySet()) {
			ParseSGTIN parseSGTIN = ParseSGTIN.Builder().withRFIDTag(entry.getKey()).build();
			SGTIN sgtin = parseSGTIN.getSGTIN();

			String expected = entry.getValue();
			String actual = sgtin.getSerial();
			assertEquals(expected, actual);
		}
	}
}
