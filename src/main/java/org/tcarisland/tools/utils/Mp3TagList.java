package org.tcarisland.tools.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.mpatric.mp3agic.ID3v2;

public class Mp3TagList {

	public static List<Object[]> getTagList(ID3v2 tag) {
		return Arrays.asList(
				getTagArray(tag))
				.stream()
				.filter(u -> u[0] != null)
				.filter(u -> !("" + u[0]).equals("-1"))
				.collect(Collectors.toList());
	}

	public static Object[][] getTagArray(ID3v2 tag) {
	  if(tag == null) {
      return new Object[][] { {} };
	  }
		return new Object[][] {
				{tag.getFrameSets(), "FrameSets"},
				{tag.getAudiofileUrl(), "AudiofileUrl"},
				{tag.getChapterTOC(), "ChapterTOC"},
				{tag.getObseleteFormat(), "ObseleteFormat"},
				{tag.getPartOfSet(), "PartOfSet"},
				{tag.getTitle(), "Title"},
				{tag.getRadiostationUrl(), "RadiostationUrl"},
				{tag.getAudioSourceUrl(), "AudioSourceUrl"},
				{tag.getGenre(), "Genre"},
				{tag.getEncoder(), "Encoder"},
				{tag.getArtistUrl(), "ArtistUrl"},
				{tag.getDate(), "Date"},
				{tag.getComment(), "Comment"},
				{tag.getGenreDescription(), "GenreDescription"},
				{tag.getPaymentUrl(), "PaymentUrl"},
				{tag.getAlbum(), "Album"},
				{tag.getVersion(), "Version"},
				{tag.getTrack(), "Track"},
				{tag.getBPM(), "BPM"},
				{tag.getKey(), "Key"},
				{tag.getChapters(), "Chapters"},
				{tag.getItunesComment(), "ItunesComment"},
				{tag.getCopyright(), "Copyright"},
				{tag.getAlbumArtist(), "AlbumArtist"},
				{tag.getPadding(), "Padding"},
				{tag.getArtist(), "Artist"},
				{tag.getLyrics(), "Lyrics"},
				{tag.getCopyrightUrl(), "CopyrightUrl"},
				{tag.getWmpRating(), "WmpRating"},
				{tag.getDataLength(), "DataLength"},
				{tag.getGrouping(), "Grouping"},
				{tag.getUrl(), "Url"},
				{tag.getOriginalArtist(), "OriginalArtist"},
				{tag.getCommercialUrl(), "CommercialUrl"},
				{tag.getYear(), "Year"},
				{tag.getPublisher(), "Publisher"},
				{tag.getAlbumImage(), "AlbumImage"},
				{tag.getGenre(), "Genre"},
				{tag.getComposer(), "Composer"},
				{tag.getPublisherUrl(), "PublisherUrl"},
				{tag.getLength(), "Length"},
				{tag.getAlbumImageMimeType(), "AlbumImageMimeType"}
		};
	}

}
