package ga.nullcraft.client.graphics.basis;

import java.io.IOException;
import java.nio.ByteBuffer;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;
import ga.nullcraft.global.game.block.Block;

public class TextureLoader {

	public ByteBuffer[] getBlockTexture(Block block) throws IOException {
		ByteBuffer[] buffers = new ByteBuffer[6];
		String[] strs = block.getTextureNameList().get(0);
		for (int i = 0; i < strs.length; i++) {
			PNGDecoder decoder = new PNGDecoder(TextureLoader.class.getResourceAsStream(strs[i]));
			ByteBuffer buf = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());
			decoder.decode(buf, decoder.getWidth() * 4, Format.RGBA);
			buf.flip();
			buf = buffers[i];
		}
		
		if(strs.length == 1) {
			for(int i = 1; i < 6; i++) {
				buffers[i] = buffers[0];
			}
		} else if(strs.length == 2) {
			for(int i = 2; i < 5; i++) {
				buffers[i] = buffers[1];
			}
			buffers[5] = buffers[0];
		} else if(strs.length == 3) {
			buffers[5] = buffers[0];
			buffers[3] = buffers[1];
			buffers[4] = buffers[2];
		} else if(strs.length == 4) {
			buffers[5] = buffers[3];
			buffers[3] = buffers[1];
			buffers[4] = buffers[2];
		} else if(strs.length == 5) {
			buffers[5] = buffers[4];
			buffers[4] = buffers[3];
			buffers[3] = buffers[1];
		}
		return buffers;
	}
}
