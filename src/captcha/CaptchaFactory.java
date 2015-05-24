package captcha;

import captcha.generator.ChineseGenerator;
import captcha.generator.ChineseIdiomGenerator;
import captcha.generator.ChooseImageGenerator;
import captcha.generator.GifGenerator;
import captcha.generator.LetterAndNumberGenerator;
import captcha.generator.SelectFourFromSixGenerator;
import captcha.generator.SimpleLetterAndNumberGenerator;

/**
 * åˆ›å»ºéªŒè¯�ç �ç”Ÿæˆ�å™¨å¯¹è±¡çš„å·¥åŽ‚ã€‚
 * 
 * @author Gerry
 * 
 */
public class CaptchaFactory {

    public enum CaptchaType {

        /** å¸¸è§„4ä¸ªå­—ç¬¦éªŒè¯�ç � **/
        LETTERANDNUMBER,

        /** 6é€‰4éªŒè¯�ç � **/
        SELECTFOURFROMSIX,

        /** ç®€æ´�ç‰ˆå¸¸è§„4ä¸ªå­—ç¬¦éªŒè¯�ç � **/
        SIMPLELETTERANDNUMBER,

        /** å°�å­¦ç”Ÿå­—åº“4å­—éªŒè¯�ç � **/
        CHINESE,

        /** ç®€æ´�ç‰ˆ4å­—æˆ�è¯­éªŒè¯�ç � **/
        CHINESEIDIOM,

        /** gifåŠ¨ç”»éªŒè¯�ç � **/
        GIF,
        
        /** é€‰æ‹©å›¾ç‰‡éªŒè¯�ç � **/
        CHOOSEIMAGE
    }

    /**
     * æ ¹æ�®æžšä¸¾ç±»åž‹æž„å»ºå¯¹åº”çš„å®žçŽ°äº†{@link ICaptcha}æŽ¥å�£çš„éªŒè¯�ç �ç”Ÿæˆ�å™¨å¯¹è±¡ã€‚
     * 
     * @param captchaType
     * @return
     */
    public static ICaptcha createCaptchaObject(CaptchaType captchaType) {

        ICaptcha captchaObject = null;

        switch (captchaType) {
        case LETTERANDNUMBER:
            captchaObject = LetterAndNumberGenerator.getInstance();
            break;
        case SELECTFOURFROMSIX:
            captchaObject = SelectFourFromSixGenerator.getInstance();
            break;
        case SIMPLELETTERANDNUMBER:
            captchaObject = SimpleLetterAndNumberGenerator.getInstance();
            break;
        case CHINESE:
            captchaObject = ChineseGenerator.getInstance();
            break;
        case CHINESEIDIOM:
            captchaObject = ChineseIdiomGenerator.getInstance();
            break;
        case GIF:
            captchaObject = GifGenerator.getInstance();
            break;
        case CHOOSEIMAGE:
            captchaObject = ChooseImageGenerator.getInstance();
            break;
        default:
            break;
        }

        return captchaObject;
    }
}
