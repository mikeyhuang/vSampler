package org.mulinlab.variantsampler.query;

import org.junit.Test;
import org.mulinlab.variantsampler.utils.GP;
import org.mulinlab.variantsampler.utils.QueryParam;
import org.mulinlab.variantsampler.utils.node.DBNode;
import org.mulinlab.varnote.cmdline.abstractclass.CMDProgram;
import org.mulinlab.varnote.cmdline.tools.Count;
import org.mulinlab.varnote.operations.decode.TABLocCodec;
import org.mulinlab.varnote.operations.readers.db.VannoMixReader;
import org.mulinlab.varnote.operations.readers.db.VannoReader;
import org.mulinlab.varnote.utils.VannoUtils;
import org.mulinlab.varnote.utils.format.Format;

import java.io.IOException;
import java.util.List;

public class SamplerTest {

    @Test
    public void doQuery() {
        try {
            Format format = VannoUtils.getVCFLikeFormat();
            QueryParam queryParam = QueryParam.defaultQueryParam(format);

//            queryParam.setGcIdx(2, GCDeviation.D5);
//            queryParam.setTissueIdx(2);
            queryParam.setSamplerNumber(10);
            queryParam.setAnnoNumber(1);
            queryParam.setRandomSeed(10);
            queryParam.setExcludeInput(true);
            queryParam.setVariantTypeSpecific(true);
            queryParam.setCrossChr(true);
            queryParam.cellIdx = 113;
            queryParam.markerIndex = 2;
            Sampler query = new Sampler("/hg19/VarNoteDB_FP_Roadmap_E116.DHS.variant.1000.txt", "/hg19/EUR.gz", queryParam);
            query.doQuery();
            query.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIndex() {
        String[] args = new String[]{ "-D", "/hg19/EUR.gz",
                "-Q:vcfLike", "/hg19/VarNoteDB_FP_Roadmap_E116.DHS.variant.1000.txt",
                "-CT", "E116",
                "-M", "H3K4me3"};
        org.mulinlab.variantsampler.cmdline.tools.Sampler sampler = new org.mulinlab.variantsampler.cmdline.tools.Sampler();
        sampler.instanceMain(args);
    }

    @Test
    public void queryMAF() throws IOException {
        TABLocCodec locCodec = GP.getDefaultDecode(true);
        VannoReader reader = new VannoMixReader("/hg19/EUR.gz");
        reader.query("9:5453459-5453460");

        List<String> list = reader.getResults();
        DBNode dbNode = new DBNode(locCodec.decode(list.get(0)));
        dbNode.decodeOthers();
        System.out.println();
    }


    public static void initClass(Class<?> clazz, String[] args) {
        initClass(clazz, args, false);
    }

    public static void initClass(Class<?> clazz, String[] args, boolean printUsage) {
        final CMDProgram program;
        try {
            program = (CMDProgram)clazz.newInstance();
            if(printUsage) System.out.println("\n\n" + program.getUsage() + "\n\n");

            int ret = program.instanceMain(args);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}