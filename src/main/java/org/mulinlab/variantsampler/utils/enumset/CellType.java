package org.mulinlab.variantsampler.utils.enumset;

import org.mulinlab.variantsampler.utils.GP;

public enum CellType {
    E001(0), E002(1), E003(2), E004(3), E005(4), E006(5), E007(6),
    E008(7), E009(8), E010(9), E011(10), E012(11), E013(12), E014(13),
    E015(14), E016(15), E017(16), E018(17), E019(18), E020(19), E021(20),
    E022(21), E023(22), E024(23), E025(24), E026(25), E027(26), E028(27),
    E029(28), E030(29), E031(30), E032(31), E033(32), E034(33), E035(34),
    E036(35), E037(36), E038(37), E039(38), E040(39), E041(40), E042(41),
    E043(42), E044(43), E045(44), E046(45), E047(46), E048(47), E049(48),
    E050(49), E051(50), E052(51), E053(52), E054(53), E055(54), E056(55),
    E057(56), E058(57), E059(58), E061(59), E062(60), E063(61), E065(62),
    E066(63), E067(64), E068(65), E069(66), E070(67), E071(68), E072(69),
    E073(70), E074(71), E075(72), E076(73), E077(74), E078(75), E079(76),
    E080(77), E081(78), E082(79), E083(80), E084(81), E085(82), E086(83),
    E087(84), E088(85), E089(86), E090(87), E091(88), E092(89), E093(90),
    E094(91), E095(92), E096(93), E097(94), E098(95), E099(96), E100(97),
    E101(98), E102(99), E103(100), E104(101), E105(102), E106(103), E107(104),
    E108(105), E109(106), E110(107), E111(108), E112(109), E113(110), E114(111),
    E115(112), E116(113), E117(114), E118(115), E119(116), E120(117), E121(118),
    E122(119), E123(120), E124(121), E125(122), E126(123), E127(124), E128(125),
    E129(126);

    private final int idx;
    CellType(final int idx) {
        this.idx = idx;
    }

    public int getIdx() {
        return idx;
    }

    public static int getIdx(final CellType cellType) {
        if (cellType == null) {
            return GP.NO_CELL_TYPE;
        } else {
            for (CellType cell:CellType.values()) {
                if(cell == cellType) {
                    return cell.getIdx();
                }
            }
            return GP.NO_CELL_TYPE;
        }
    }

    public static CellType getVal(final int index) {
        return  CellType.values()[index];
    }
}
