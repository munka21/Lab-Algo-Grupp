import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PrimarySchoolTest {
	private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    
	private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;
	
	@BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }
	
	private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }
    private String getOutput() {
        return testOut.toString();
    }
    
    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }
    @Test
    public void testCaseSample() throws NumberFormatException, IOException {
        final String testString = "4\n"
        		+ "carsten einalem crasten andreas\n";
        provideInput(testString);

        PrimarySchoolV2.main(new String[0]);
        
        
        final String expectedOutput = "3";      		
        assertEquals(expectedOutput, getOutput());
    }
    
    @Test
    public void testCaseNoNames() throws NumberFormatException, IOException {
        final String testString = "0\n";
        provideInput(testString);

        PrimarySchoolV2.main(new String[0]);
        
        
        final String expectedOutput = "0";      		
        assertEquals(expectedOutput, getOutput());
    }
    @Test
    public void testCasedifferentLength() throws NumberFormatException, IOException {
        final String testString = "4\n"
        		+ "anna ana annalena enalnnaa";
        provideInput(testString);

        PrimarySchoolV2.main(new String[0]);
        
        
        final String expectedOutput = "3";      		
        assertEquals(expectedOutput, getOutput());
    }
    
    @Test
    public void testCaseOneNames() throws NumberFormatException, IOException {
        final String testString = "1\n"
        		+ "hurzelpurzel";
        provideInput(testString);

        PrimarySchoolV2.main(new String[0]);
        
        
        final String expectedOutput = "1";      		
        assertEquals(expectedOutput, getOutput());
    }
    
    @Test
    public void testCaseSLotsOfNames() throws NumberFormatException, IOException {
        final String testString = "1000\n"
        		+ "zefczkwuhj zefczkwuhj zefczkwuhj zefczkwuhj zefczkwuhj zefczkwuhj zefczkwuhj zefczkwuhj zefczkwuhj qrixfimolo pqjnhbgfnx jpfuhqgyqg aagciyqwvp oeccrvqzzq zjulgodqlz oearzunaio xzcpbqskmq jjiycihqkx vrtzccgfyy yukegkbzut syuiaczedx vdcywlqjyl tgfztkedfe ubfhlucpmo jzrohfoule torpzpzmaw pkqcdlktlz sbmpkbyyso cjgrsllptn wbhonfqchv vkcwbjcjoo oosyjvqlhn gabclxyphm syfjnccnpm ufgxszfwgd pjsntrxbtk hsshhzlyns csirofxkcp rczisnsoqf sbeiemngzy avwtaymnlx sndrvhlwls bzqnpaenfi spolykodud pkqtqownxi eopsbwgcxf peunughxat cbaxbhbqmr hjzzefhhva qaemkbbunu zkqixqtbjv xsqzguraui ktoshypacd isxqbnefdx zrhrzwhkrg qhntgllbau zprnfciuyw jrynkfrgjz ysswvjrrwd hcoopisusr pcufyzyvhh rjywyxguib tpqkwmrbxc evoparvbje qvhwmeyzhr hltmfcgrng ecsftpfehu buazjtlcjp ktwsnludez jddlyxsqxn kzqcjyxtat czposgkmjz bkwqparqud alfuvlchnm htjunpuuch tpyeqbxkyn wkxiekvbuy mqprfqnmnr dgxvmcjows pzvhaxsjos varcwfupfi kexykckizh dzzcxwzexa ncrjuzhlxt tbyzzbzeqw siljdxcndj yrhvghnuzi zvmsruxydz tfbrfllwqp pdothlrcep cusaxneeva wednnezhbi ofbzjxruuk oxhzmdlgfj cgcdpnuzbv fnumbgysge norgtgwviy ebkstmihob akxtequzcs mmzjkzclxr tbhdcckesy pemhxnvzde ksuzduudkj nfqqwysexv aoexhpawpi iymiuyldsh xycgnvxxnu ozqfwytjfq repijpowud kexrglklqo pyvkshocmu auxfcbyoqw lrzegtqdae xeeeusguik znceqzvxfy izlcvhvhhm tzfbmgpwqq tovmjsyumi yuxgaelkgb xwfjinyklk uzpubcvsxk btjxisrsbv cnglqiqjyc rqkynxijyk tkzkosvuec naamnxkrio xrnjmhderh vqvhnyemnx sgmenzphxu jnsljtwjbl szlwmdmgla burqlvbgvx kqsmqdmxsa nywbfntcip aphdvmitxa apvrozbbhi mvuslcmkyd nwahwecvpv odvcqtfbbk pgvaryiluk dbxclnqoaz mzntembekr ctxtwndqrh gmgcmafcxw ynbrvrkbyg kfhohiwnlg yqmiobtykh ntwidxxall cvwxrvsppz dqaqvagibj rqxtriinhn jqbgwhiwcz qghygqutgg cbibwpfbsx fnmdliivel oxuqxohwjw sjegspnnei ubvebutffl ubganulsmy sdbwfzdrbc bjyypflgsi jpmaiuqhrq ehckeogupy btwzuosysy zjxtasdwko xmspukunvh vnczscpikq ovbvqnxidj chwdlehsbp smytkozrdc nrhzqfkvsb onorgfrtso aoyghnamhw zzkkyvnijb ncjvjuouvu fctvrhurxg blzhhsoapl vdomcmtioe valfhcszut hbmvagwnzh epteefmxeh aunlqlqvqt xvqzveklei itcvfkemwn thhmkcjmib cnvatgujpj cidqjstogu tpsypraavu jjvzxtxapp vvocrzxfsr dncugyutfz mfacymsqel xqrxbjavhm cnrpjmvvpr ylmkgsxkzc amexuvjsqh ocriwpvcqm onzrvnrfpe bylagmfsum uttlhreqba gxfuolonwp uzvkijqzaf maqnvofmop hounbmdkco osjgjzpxvh zuqmbxgnht drtkrqtthc mzxcomikxd bazmvxyrqm dusoawpkos ugcshtcjwe fwatckfhlp orqsxfkokv rnesplfrwu cbczmtcsoh ptekjwcfng pckosjjrck focpnnltoa tnhvuvecwf zkmsishhyh wmsqpnduvo pbsnzaptim olmhmpysch taatnijfsw bakuoldpyr muqvgjgnoq lufjvkqkbp slpcfmebvq kcbxlzferu fqohbmxdss jwhsyaqxvc bdqxqoaqvy vielusgbhs spyoxfxecg xclfecpfuv aprqqeaspi ttiosrqjiv qmbvitzbvf asvzxjzzwn ntdtwuuoqa jkbilhzwfe fxhluykxdt rpvvulzloa ltoiocxbdw nwhyuyfnzw uuowoopvvf ckrfnmgnpv bnvlpflkdc umhztghyan cmginmmeaz uoynxqvxqy oonvvmjclh hvtxlpgytm dlvxuyrtsq idszjilqte vzajvzbuxi ndzvsfyhee cfxgpiyonz cziarjshhg nixurcmuae msrtwnuqrl wuajatzmqy rguksrutbd nztsiccigc dkersqhuzl hwzexjuzrz hhpggskpjj wnnfcovrvs skpkwwgwgj yrhapzpobw tethkbkfkc dplfwparys lpfentqasj svuhdrasvx nrparjlxef svknlhznnx rkdqbupybo cozciwtwmi csdcpxaqcl zupqnfruzm qdzogsiran zxdstxumun zeatibtvzf cvxsuvuuep negjpmdzbe rkkqjuiobc krxdpitgqh jttxdzfybc ejsdazrdip nktlhkylhu mjrmrxwtmd wjayguhtuo kvpevtgpki dwedcmhgnk rwkhrjznjn msfetfrsbx bhdvickoin lrilhbxefi jjfqkcvtnf bbdfrwnbzd oykusrvqnz fcfyhvkosv ntvlkryyft jxczibmuiw wwrgklvifg qkqrzuzxcd yqbrooulkt gmjsnupsqu qorhgwmpcn tzumbywznu txypwlmhbl kpsgcxfoyj gtlewndpnl xvmqbgqqyy qpajziwkkx yqidvwhfie qkssjpiedp kjitsvavpy vsqnxmttwd iczwhbeore qgbndxyelw vbcjyolert dekgmsatjl biiozkbtfr aesfbhsary ohwpixfgdh gdcggrgkpv qnrqfurzdh ffqrydsayn ywdxwxhvva hvuipbxivb qyrljmwifp rqbzchvrza zxnuiayrlr bjzhoouggn ouhinhyoto izheotfnox nbglwvrdhy czeqeriovr fmlutmsimm xkoensoita mdqnqqfcca nrxlxgjhnf xvaosulfiw flfmdpbsgq hdcwwfyllx bpknnbvsap viksrisiaa mnxjkljqpl lmhokkedjz mkihcltreu emedyopzly oncrswylaa ecztelvpjh nblphylwmx opkgsbfent snnxgwixpq elribrvqvz podimxocct pqgmlgjlth wtzlapydvs qqethheypn xdszfwlvsu qufviggnai gstkezlxnz rdnbvosggg xybmtesklz wvlfsdatbl ojlplrcrdk vlhkkevwzh gaihdavmqa zvfuhlwhrm hfpqcyhcza wjqiwfyqqp avfhalfoho wnmjphjigs gqpjmifpwg nypmcfayfg kycxijrtjp nexjqxpqdv jdlzicrtna budhsnnnch dzkyiztwfe jvgjcpfece cpwltmsseg snxdtunixx czqidrluie lehdrhutfk elaulehbyo ldmevsdvtb llzqkkqojw jpmsqvdqil gifqxgiqyh mpvrdcfrsv mbuugmkjsf tbydlvbagj ahzkgcelzz zosyzmdrrr mxhwmhaxor hzwjrjenvf ehoqkkdysl bcptmkujsi sqzspgzpkh lsohzbpjtk ejkzrhjsyx ornagcleme gmnfwnijau bcaqnrykpx pahgfpwtiv furrzvcang usbqsjuiqr sfqthaihfi lfltoelrsn kszyqbbdrn vsonmwvrrb bjsrynsbwe htdfswqivn lvqxbgxdyw koshowqoug pjqnfsmudf snbmesyved vfcmgzljhu zxecuznhop uarpdaknoy hebbagpvsp mdthcdfizc daoxhimivb jvmjsuapcc tebiabkmuy iqnggzfeve nwdcatviai opkzsduhtr mrdkbxtbjb phvhhxxsyv qtbaoqunbs cqpgqvuppe dihcwgyayb lkfbaewegd oykkdggwqn iipeemutno boosyjcibl wbxiaunxdk gnpwxyjufo xlnckkkrwx zpekoqwhct hodkozppgg axazlrdcoy fspzopouzd sxjbateyfr qfcnwmylgm moamujwesw iweubatogj ixjpjatpiv iweruqnrpw wratrtilzr rcinueycra gnpxzkhzgh cjqfgfshgb ugemwqotkx donuaahfna weqqqgfwge jtainfrtvg qdsikiqdxi bagpbkhdul vpbleffsmv pcrmnwxlly lhkkcqzijm xlgbidpeqd yteolgvdcs xcaoveccnt nxaxuywwca iieeldpmrp bekikyzwdn ncllifqapa hwjilfbuuo zfmnqtxkqs xororhgltw wualbefiqd gcboootncs qnwnuwluwx iiyfaunkhv ctzvktrlmq yieosxgpfy uobzoncqhg qtrntarmvk ywgyhuxnij hhhdciltkj xkspwmzuxy wqfyscbkzc cxyzrygzuo kopbutcxzu fffgizerty xtislpzqhq wbfffomuyl olvdwurion ocijefnzen xtwjremqkp izaoqjmutu srkvxxspqh flxdkteaku qwhrtdkohy wqwthradwh olozkjnspm jqudfsyyue hgysybvuyn byhcxtvemd abnqwordki rkramkrddc phiaadqiwb nishcrqwxm kfuzykppnl ecbrhtpmaz uasxeroybf vyxshyeubr elxqjidrwl krpeafoogu ymoharxjgt tktnklmubt opsakgziam kxrzcvqzqu pdjtxyzjvi wzwyjcdifa lwgatwgrey hduxgbznjw hjsqrjcmop hdsibgastg okhmixgsli rajkagvdol dqxobzcknm manyngjhnj bimnlstqsu atugevyhxm bdtshqerpm mxdgyyhtza phokgmcpgd tnfqhckyyv eaqxoltrpq mevfcorvff aviivrxopo vydrrscwya bsimzxsmah elpeodyudv atzorexfeg oavhnoiwyw pauskbhlre ulitouhuzb uyhfarxlai cgckukaaox ypouizgyer uavwzymgtx mbdprnvsaz yluwwygzeh gojrqikcdy asnxxphgby cqsdifnmin fgqvljatwt emnaqrlaim pxhvsdvfrs arkfxahgqj vumzaofqtl mjoowhiqhv glmwqaeobc baelrmezhi vnubskjjrn vnaxbgqfvx wmzsykjppt olpgfdnioc srlmnzsrcl nqaorlhsjk avzphssgpp xhbpweghhp hzgjnpoavc evcakrkwgu rzmfoqnlmy nnzrhxazsp ecjrdnztrm jyjlpbberh ijmxphesjw jpbtwyuitz pjrbspkgjz dzgatjrnkk miqkeepegf nccmijiuvt hhyyueswij okkzbgpglg elpvqddxua icyeilqwqm jhremaejhy ekjrklepht pyngqdfgij jdsvphjibs zwdpheoitd rpknbsjddw appvsvhtpx poqpcnizga guoulutgyw grkrazlzeo ljzjiyerpa uwoyhhvaoa vxzkplaiyv hxmoywpeag nmrkoodmhr fpepywklyj niyqmzpijc nzluyyzufk pchuwuztyc zyftzajrfu lljaqkcihn glrtctasox eirsssqyvb xyjyugdfer egcmixjulh vbcqrtfuzy yvczaypzkf iuxkhnkejl toturecrcf zdyervgtet nxpqafayqt ddnlifldmk bbdimqkknr pyebptpftr uvffgmjrsh yjnjwdztqk kpkknqvrrh xncrcskfnr fnhdsylvil qguhjdgrfl feoxoaojel hoxaflrawc uhufouuecu scvtqvvpjj agyilzwqsk lyuvuahcef lgafgecvnx cftqkwvdfz cglnldxkuw haejvfquwp jqnutjwgwe wevybykkzm soiivgbvws pvghxmybtb axljribvcj bryrjpyszy bbcntuqapt cwtyfgrqoj eywxifvvry kfixohaasg cilskrebxx jwrhgpfazd kdturkypll dtgciejamq ozyrsjpzaf pvvootytrf urvnldsxmi qbejwevqsm uewebociun axoliddugx nakaoppitc jicilyjana cvjcjdmapi knsgzpjtbb gbhwetriam kdobmqchxa xrpkbutbrk dfpqndxghp hnzxnxtesw ziqtoolwyv pewwsseuzf xwjmldhcsw tobdduwntc teibalcypb eroxsfjuxy ffomcjsskd vzywpeakqp vcertqxvcm tolmepgcgi lqjruwdtnd vtartthyoz plycnrwodi ycxstcqgzc jonnpuahni ngdpcpqrdx wtvdrzaufa eokndrztuq kzopgtvwvh ogzlphecjr fekmcfokok ustrcgydkd efmexpdwiv lfjwmudbio ccakkwlfsb ffbzrguzxc zkavzygjrq bbfplvwhkr ieiewovuvl ukdukivfam anwnguxtcj kxebhbtpjs wcvarhmnai ultbeqmips iaadordhsh yrpemnlafm djkvgravkt rvtrsbikan qyjdrrawwb rjzepvbecw odjoqwbnya gkkhltmcbn unqshhdesk afiwibwmae lphndeoswq koikyxxbmh egbnhjcrka kmgyfpclww jvawjmrbux nudpdmgvjs hrnblgylpo ojtikfvhtz aehftxtfgf dzzmvcktke cvufowcegm kvpriksnhg delxzhuilp uhecwxmqrv whbngpntit nuhvxfuxvt qukqmkrobf zspdvympti aehpsuyhma ziqpqwbrbo uaogzujaep rcyqooajmy hpghewtvxj bfclenpxrv hdhlvdabue xzglpodaqk vaqcyajvmk bfhoqlovfv oyucvqgeib uyfjezsfcl hbtrpoebvk cgnflwtioq yyoonvgixs edpikglrpb peodeouwpy wbhqlljdgj zafdhupydi sidblnkmjw adpuzljxts zcohbyumeu gbgdzincvg lqhqyuqfpi oiilecnqnc fqirzvbvxt kougqodtky nlbbzubofj vegngdvmlj jlbbvlzbwo tjgwwyeqio mliocujknp zfiialknqw yhobbuchfc loqixfgxxn mszxhpdlpv nnexmzprjd zlqxmtuqji ooodelqgkd iztvfrqvcm zbxupwuusg fjofpnzszr gcelsmstzc gbnypsuubu uqojyhfyni mumihyujst dwjlqvjjlh nxzmwrwtbz wtwwscpwqp jpdelcmfql qidaegyzha vjswcmmktz ysahcwinwk zgndoqtzvx dxjchxrvmz rpsxjfzkpp ubethdotyk omdsyfhhtl fefqnxaqeh wnozbkdidq lccxgpnwlm kfmwtovwll vkgevkfooa cmuywnmfcp akrcsnuwmf izrewwhnjb oritwwqhoq zbnzqntsai phzakskeel tuqcpfpnbt dhkcwzrxwu fjmktetyor wgeizadmnu nyftcxoiek yesukifhgj ejmpshrnfg wljihhasve scaoitbuki oovpnarqib brhydtqenk inzsfvknrn ndnjwxfcky miltlzanmp hrsbfhijfa uktcaqqmac hozojfemqq dfundnxenb gxyaaeuimh pzbglzscrn ydinimvdmc tubntebrbi aemclroykn kvbutsjfge yptfmeznrx yudltgukot mwudtsmcwt thhllhjldt arvgeftgjf pkybwklvoi gpncjqwgdo wemjdqmlzv ucesnnaoyt ooifowdhtk jyivjqwzdg fwakygajph utjsgsfaoo jytevkevyk nanxaytkvx miaazjpzkn xycmindlji vzfuufombs rezpptqjhw keffijbxce qeczgziget rwgpyozvmr eeptdhscqb algxnwncye nhcemhyngw qnqwbqekdg fpnlzgvvcp uwkmjwzrkn vytybdjgrm ujeyeeqooe oareegartp ghyzojkztm oyuargoymr kmnbskloch hxegvgreqn xdbamyjmyu ypqvnlduhx rehxjceqdj vkhhwdtivq jwxazuyujj iesnkjzxkp hwgstzbdam vqzqefkrcb esynxtqofd bctuceihnd djavjspuyv ikorcabjqx ebkwrncxgy qwknezyivj xeypbhexiu godvjsdlot kmqyszrjyk fhqcsxfgzo nrmrsahpjw kaddnlrwkg tppzqintrn avirobbxwe saflzmhnfa vtzygoxtvl jolckeifsz fxjxaymqcs pymmegzcrr nokmygdbwy vllzmvarqr vakhflhdpj qebjypudqx hnydwnhago hsbliltbtk xgysmcyupx ztcsdkvlhj cfgsxjwwpl inhcgnczos wrskpihqlz dqucikjiyc jmsllnzyhx nfkxwcgbjl pucqqdfbvz kgkefqjlsu ozkpvcdmkd qjlleyjwdz pvqtxwdpmj mnxfiswmcd svjwljkqgl htdhfftojz mzpzerifer puvxmkndpn lkotlurdae vdlabopkgq ebpxknefyg pvicgkttbq ybredlevjr plhleoxajg aweehquswf aabsnsbowp hyjyrnbolp uetesjmppy jehfnvayby jhzimoxslw buviwrfqta glvmwyuvqo gyaqmqseqx zjxjiiwbyp zuejeqpiae ajbrsicktf qnjbrxdstq kkbhanlmvg fobfsgvthx qreihyhurv zrnibsbebv tzmkacvixv eqseubiams hhzhxnyzrn qpkvfbmmhf oaeikzewgb wydhzsffwe hjrxvwstut ywuoulkhda lgowgikjva hdnpnnwuoh lzkublimze gbbfuvwqkw onkwuhoryl fvygqtaatn maxkpdmluj kmcqrhhomt zjsomfbtij joyvklwyek qugkgmsoiy zjycioqjqz vfvfbnlwna jdydwgyjpo pgepfjmqeu qatywgjtzr hgjqpgmlij wxzcbyqqmt tceedljntw jnbwainjzq ibsvhfxfet aveugxtxpx pdnartggli jrphaiodas bpcbxgwvmm lkjbrciswb ebsuyqeufb rjpoibaxwc iafdaorqds woehdaivfu rujxmccnxa cfdnbtntfs lxfsojrrfz apvtambjue ljffqqayye fdlpbjqfim rhrqcmllob albvirkkjy ljjuaidtck snhgqivhlk oepcgymhkk hmqupxjxzz cbfdlscblw mhdcssqkeu bfrrhgjfpl czrvqaprxi rdfdjzyayv rtgvoxllqp ukjhbliieb onecrzlhiw cahhggutwo wlkjzlzmyq gfflzikzdy qlmmgpktim ihcathxbid lmtiitnwjn pyfqagmaqh mdyevtdjnp qbfsolckop bahuqwicwa gufgcnzyej rvahfoghpd\n";
        provideInput(testString);

        PrimarySchoolV2.main(new String[0]);
        
        
        final String expectedOutput = "992";      		
        assertEquals(expectedOutput, getOutput());
    }

}
