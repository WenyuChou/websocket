package com.polycis.websocket.tcp;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.springframework.stereotype.Component;

/**
 * @author : Wenyu Zhou
 * @version : v1.0
 * @date : 2019/7/29
 * description : 描述
 */
@Component
@ChannelHandler.Sharable
public class DiscardServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        try {
            ByteBuf in = (ByteBuf) msg;
            byte[] bytes = new byte[in.readableBytes()];
            in.readBytes(bytes);
            System.out.println("传输内容是");
            System.out.println(new String(bytes,Charsets.UTF_8));
        }  finally {
            ReferenceCountUtil.release(msg);
        }
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 出现异常就关闭
        cause.printStackTrace();
        ctx.close();
    }
}
