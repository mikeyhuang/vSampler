package org.mulinlab.variantsampler.database;


import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.mulinlab.variantsampler.utils.DBSource;
import org.mulinlab.variantsampler.utils.enumset.CellType;

import java.util.BitSet;

public class RoadmapAnnotationTest {

    @Test
    public void testQuery() throws Exception {
        DBSource dbSource = new DBSource("src/main/resources/db.ini");

        RoadmapAnnotation roadmapAnnotation = new RoadmapAnnotation("/hg19/VarNoteDB_FP_Roadmap_127Epi.bed.gz");
        long[][] r = roadmapAnnotation.query("1", 948692);

        System.out.println("\n\n");
        BitSet bitSet;

        for (long[] p: r) {

            System.out.println(StringUtils.join(p, ','));
            bitSet = BitSet.valueOf(p);

            System.out.print("aa=" + roadmapAnnotation.getValOfIndex(p, CellType.getIdx(CellType.E116)) + "\n");
            for (int i = 0; i < CellType.values().length; i++)
            {
                System.out.print((bitSet.get(i) ? 1 : 0 )+ " ") ;
            }
            System.out.println("");
        }
        roadmapAnnotation.close();
    }
}