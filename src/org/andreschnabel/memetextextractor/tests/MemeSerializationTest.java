package org.andreschnabel.memetextextractor.tests;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.andreschnabel.memetextextractor.Meme;
import org.andreschnabel.memetextextractor.MemeSerialization;
import org.junit.Assert;
import org.junit.Test;

public class MemeSerializationTest {

	@Test
	public void testSerializeAndDeserializeMemes() throws Exception {
		List<Meme> memes = new LinkedList<Meme>();
		memes.add(new Meme("path1", "detext1", "entext1"));
		memes.add(new Meme("path2", "detext2", "entext2"));
		
		File f = new File("memestest.txt");
		
		MemeSerialization.serializeMemes(memes, f);
		Assert.assertTrue(f.exists());
		
		List<Meme> memes2 = MemeSerialization.deserializeMemes(f);
		Assert.assertEquals(2, memes.size());
		Assert.assertArrayEquals(memes.toArray(), memes2.toArray());
		
		f.delete();
	}

}
