package captcha.generator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import captcha.Captcha;
import captcha.ICaptcha;
import captcha.util.ColorUtil;
import captcha.util.Colors;
import captcha.util.FontUtil;
import captcha.util.RandomUtil;

public class SelectFourFromSixGenerator implements ICaptcha {

    private static SelectFourFromSixGenerator selectFourFromSixGenerator;

    private final static Logger logger = LoggerFactory.getLogger(SelectFourFromSixGenerator.class);

    // 默认验证码位数
    private final int SIZE = 6;
    // 默认验证码宽度
    private final int WIDTH = 120;
    // 默认验证码高度
    private final int HEIGHT = 28;
    // 默认字体大小
    private final float FONTSIZE = 20;

    private SelectFourFromSixGenerator() {

    }

    public static SelectFourFromSixGenerator getInstance() {
        if (selectFourFromSixGenerator == null) {
            selectFourFromSixGenerator = new SelectFourFromSixGenerator();
        }
        return selectFourFromSixGenerator;
    }

    @Override
    public Captcha generateCaptcha() {
        return generateCaptcha(this.SIZE, this.WIDTH, this.HEIGHT, this.FONTSIZE);
    }

    @Override
    public Captcha generateCaptcha(int width, int height) {
        return generateCaptcha(this.SIZE, width, height, this.FONTSIZE);
    }

    @Override
    public Captcha generateCaptcha(int size, int width, int height) {
        return generateCaptcha(size, width, height, this.FONTSIZE);
    }

    @Override
    public Captcha generateCaptcha(int width, int height, float fontSize) {
        return generateCaptcha(this.SIZE, width, height, fontSize);
    }

    @Override
    public Captcha generateCaptcha(OutputStream os) {
        return generateCaptcha(os, null, SIZE, WIDTH, HEIGHT, FONTSIZE);
    }

    @Override
    public Captcha generateCaptcha(OutputStream os, ICaptcha iCaptcha) {
        return generateCaptcha(os, null, SIZE, WIDTH, HEIGHT, FONTSIZE);
    }

    @Override
    public Captcha generateCaptcha(OutputStream os, ICaptcha iCaptcha, int width, int height) {
        return generateCaptcha(os, null, SIZE, width, height, FONTSIZE);
    }

    @Override
    public Captcha generateCaptcha(OutputStream os, ICaptcha iCaptcha, int size, int width, int height) {

        return generateCaptcha(os, null, size, width, height, FONTSIZE);
    }

    @Override
    public Captcha generateCaptcha(OutputStream os, ICaptcha iCaptcha, int width, int height, float fontSize) {
        return generateCaptcha(os, null, SIZE, width, height, fontSize);
    }

    @Override
    public Captcha generateCaptcha(OutputStream os, ICaptcha iCaptcha, int size, int width, int height, float fontSize) {

        Captcha captcha = generateCaptcha(size, width, height, fontSize);
        captcha.setCaptchaImage(null);
        try {
            ImageIO.write(captcha.getCaptchaImage(), "png", os);
        } catch (IOException e) {
            logger.warn("验证码写入输出流发生异常:", e);
            return null;
        }
        return captcha;
    }
    
    @Override
    public Captcha generateCaptcha(int size, int width, int height, float fontSize) {
        Graphics2D g = null;
        String value = "";
        BufferedImage bimage = null;

        try {

            value = RandomUtil.getRandomString(size);

            bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            g = bimage.createGraphics();
            // 设置随机背景色和字符颜色

            Colors colors = ColorUtil.getRandomColor();
            Color bgColor = colors.getBgColor();
            Color charColor = colors.getCharColor();

            g.setColor(bgColor);
            g.fillRect(0, 0, width, height);

            // 设置字体
            Font charFont = FontUtil.charFont;
            Font charFont2 = FontUtil.charFont2;
            int y = height;
            int xp = 3;

            // 设置文字颜色
            g.setColor(charColor);
            // 每个文字都变形

            int[] randIndex = RandomUtil.getRandomIndex(size, 2);
            for (int c = 0; c < value.length(); c++) {

                if (c != randIndex[0] && c != randIndex[1]) {

                    // 获取文字变形设置
                    AffineTransform fontAT = FontUtil.getAffineTransform(20, 23);
                    charFont = charFont.deriveFont(Font.BOLD, fontSize).deriveFont(fontAT);
                    g.setFont(charFont);
                } else {
                    charFont2 = charFont2.deriveFont(Font.PLAIN, fontSize + 5);
                    g.setFont(charFont2);
                }

                // 打印字符并移动位置
                char a1 = value.charAt(c);
                String ch = String.valueOf(a1);
                int ht = RandomUtil.rand.nextInt(3);
                if (a1 > 47 && a1 < 58) {
                    xp += 1;

                } else {
                    xp += 2;
                }
                g.drawString(ch, xp, y - 9 + (RandomUtil.rand.nextBoolean() ? -ht : ht));

                // 移动指针给下一个字符.
                xp += g.getFontMetrics().stringWidth(ch);

            }

        } catch (Exception e) {
            logger.warn("验证码生成失败:", e);
        } finally {
            if (g != null) {
                g.dispose();
            }
        }

        Captcha captcha = new Captcha();
        captcha.setCaptchaImage(bimage);
        captcha.setCaptcha(value);
        return captcha;
    }

}
