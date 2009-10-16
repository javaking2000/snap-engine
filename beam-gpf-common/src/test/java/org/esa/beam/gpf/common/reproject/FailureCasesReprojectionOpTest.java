package org.esa.beam.gpf.common.reproject;

import org.esa.beam.framework.datamodel.Product;
import org.esa.beam.framework.gpf.OperatorException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marco Peters
 * @version $ Revision $ Date $
 * @since BEAM 4.7
 */
public class FailureCasesReprojectionOpTest extends AbstractReprojectionOpTest {

    @Test(expected = OperatorException.class)
    public void testEmptyParameterMap() {
        createReprojectedProduct();
    }

    @Test(expected = OperatorException.class)
    public void testParameterAmbigouity_wkt_epsgCode() {
        parameterMap.put("wkt", UTM33N_WKT);
        parameterMap.put("crsCode", UTM33N_CODE);
        createReprojectedProduct();
    }

    @Test(expected = OperatorException.class)
    public void testParameterAmbigouity_wkt_wktFile() {
        parameterMap.put("wkt", UTM33N_WKT);
        parameterMap.put("wktFile", wktFile);
        createReprojectedProduct();
    }

    @Test(expected = OperatorException.class)
    public void testParameterAmbigouity_wkt_collocateProduct() {
        final Map<String, Product> productMap = new HashMap<String, Product>(5);
        productMap.put("source", sourceProduct);
        productMap.put("collocate", sourceProduct);
        parameterMap.put("wkt", UTM33N_WKT);
        createReprojectedProduct(productMap);
    }

    @Test(expected = OperatorException.class)
    public void testUnknownResamplingMethode() {
        parameterMap.put("resampling", "Super_Duper_Resampling");
        createReprojectedProduct();
    }

    @Test(expected = OperatorException.class)
    public void testMissingPixelSizeY() {
        parameterMap.put("pixelSizeX", 0.024);
        createReprojectedProduct();
    }

    @Test(expected = OperatorException.class)
    public void testMissingPixelSizeX() {
        parameterMap.put("pixelSizeY", 0.024);
        createReprojectedProduct();
    }

    @Test(expected = OperatorException.class)
    public void testMissingReferencingPixelX() {
        parameterMap.put("referencePixelY", 0.5);
        parameterMap.put("easting", 1234.5);
        parameterMap.put("northing", 1234.5);
        createReprojectedProduct();
    }

    @Test(expected = OperatorException.class)
    public void testMissingReferencingpixelY() {
        parameterMap.put("referencePixelX", 0.5);
        parameterMap.put("easting", 1234.5);
        parameterMap.put("northing", 1234.5);
        createReprojectedProduct();
    }


    @Test(expected = OperatorException.class)
    public void testMissingReferencingNorthing() {
        parameterMap.put("referencePixelX", 0.5);
        parameterMap.put("referencePixelY", 0.5);
        parameterMap.put("easting", 1234.5);
        createReprojectedProduct();
    }

    @Test(expected = OperatorException.class)
    public void testMissingReferencingEasting() {
        parameterMap.put("referencePixelX", 0.5);
        parameterMap.put("referencePixelY", 0.5);
        parameterMap.put("northing", 1234.5);
        createReprojectedProduct();
    }


}
