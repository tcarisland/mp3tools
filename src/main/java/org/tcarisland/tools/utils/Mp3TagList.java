package org.tcarisland.tools.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v2ChapterFrameData;
import com.mpatric.mp3agic.ID3v2ChapterTOCFrameData;
import com.mpatric.mp3agic.ID3v2FrameSet;

public class Mp3TagList {

	public static List<TagFrame<?>> getTagList(ID3v2 tag) throws ClassNotFoundException {
	  return Arrays.asList(getTagArray(tag)).stream().collect(Collectors.toList());
	}

  public static TagFrame<?>[] getTagArray(ID3v2 tag) throws ClassNotFoundException {
	  if(tag == null) { return null; }
		return new TagFrame<?>[] {
		      new TagFrame<Map<String, ID3v2FrameSet>>(
		          "FrameSets",
		          (u) -> u.getFrameSets(),
		          null,
		          Map.class),
		      new TagFrame<String>(
		          "AudiofileUrl",
		          u -> u.getAudiofileUrl(),
		          null,
		          String.class),
		      new TagFrame<ArrayList<ID3v2ChapterTOCFrameData>>(
		          "ChapterTOC",
		          u -> u.getChapterTOC(),
		          (a, b) -> {a.setChapterTOC(b); return a;},
		          ArrayList.class),
		      new TagFrame<Boolean>(
		          "ObseleteFormat",
		          u -> u.getObseleteFormat(),
		          (a, b) -> a,
		          Boolean.class) ,
		      new TagFrame<String>(
		          "PartOfSet",
		          u -> u.getPartOfSet(),
		          (a, b) -> { a.setPartOfSet(b); return a; },
		          String.class),
		      new TagFrame<String>(
		          "Title",
		          u -> u.getTitle(),
		          (a, b) -> {a.setTitle(b); return a;},
		          String.class),
		      new TagFrame<String>("RadiostationUrl", u -> u.getRadiostationUrl(), null, String.class),
		      new TagFrame<String>("AudioSourceUrl", u -> u.getAudioSourceUrl(), null, String.class),
		      new TagFrame<Integer>("Genre", u -> u.getGenre(), null, Integer.class),
		      new TagFrame<String>("Encoder", u -> u.getEncoder(), null, String.class),
		      new TagFrame<String>("ArtistUrl", u -> u.getArtistUrl(), null, String.class),
		      new TagFrame<String>("Date", u -> u.getDate(), null, String.class),
		      new TagFrame<String>("Comment", u -> u.getComment(), null, String.class),
		      new TagFrame<String>("GenreDescription", u -> u.getGenreDescription(), null, String.class),
		      new TagFrame<String>("PaymentUrl", u -> u.getPaymentUrl(), null, String.class),
		      new TagFrame<String>("Album", u -> u.getAlbum(), null, String.class),
		      new TagFrame<String>("Version", u -> u.getVersion(), null, String.class),
		      new TagFrame<String>("Track", u -> u.getTrack(), null, String.class),
		      new TagFrame<Integer>("BPM", u -> u.getBPM(), null, Integer.class),
		      new TagFrame<String>("Key", u -> u.getKey(), null, String.class),
		      new TagFrame<ArrayList<ID3v2ChapterFrameData>>("Chapters", u -> u.getChapters(), null, ArrayList.class),
		      new TagFrame<String>("ItunesComment", u -> u.getItunesComment(), null, String.class),
		      new TagFrame<String>("Copyright", u -> u.getCopyright(), null, String.class),
		      new TagFrame<String>("AlbumArtist", u -> u.getAlbumArtist(), null, String.class),
		      new TagFrame<Boolean>("Padding", u -> u.getPadding(), null, Boolean.class),
		      new TagFrame<String>("Artist", u -> u.getArtist(), null, String.class),
		      new TagFrame<String>("Lyrics", u -> u.getLyrics(), null, String.class),
		      new TagFrame<String>("CopyrightUrl", u -> u.getCopyrightUrl(), null, String.class),
		      new TagFrame<Integer>("WmpRating", u -> u.getWmpRating(), null, Integer.class),
		      new TagFrame<Integer>("DataLength", u -> u.getDataLength(), null, Integer.class),
		      new TagFrame<String>("Grouping", u -> u.getGrouping(), null, String.class),
		      new TagFrame<String>("Url", u -> u.getUrl(), null, String.class),
		      new TagFrame<String>("OriginalArtist", u -> u.getOriginalArtist(), null, String.class),
		      new TagFrame<String>("CommercialUrl", u -> u.getCommercialUrl(), null, String.class),
		      new TagFrame<String>("Year", u -> u.getYear(), null, String.class),
		      new TagFrame<String>("Publisher", u -> u.getPublisher(), null, String.class),
		      new TagFrame<byte[]>("AlbumImage", u -> u.getAlbumImage(), null, byte[].class),
		      new TagFrame<Integer>("Genre", u -> u.getGenre(), null, Integer.class),
		      new TagFrame<String>("Composer", u -> u.getComposer(), null, String.class),
		      new TagFrame<String>("PublisherUrl", u -> u.getPublisherUrl(), null, String.class),
		      new TagFrame<Integer>("Length", u -> u.getLength(), null, Integer.class),
		      new TagFrame<String>("AlbumImageMimeType", u -> u.getAlbumImageMimeType(), null, String.class),
		};
	}

}
