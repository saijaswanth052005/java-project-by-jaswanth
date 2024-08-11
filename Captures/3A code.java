// MusicStreamingApp.java
public class MusicStreamingApp {

    // MusicPlayer interface (Abstraction in Bridge Pattern)
    public interface MusicPlayer {
        void play();
        void stop();
        void pause();
    }

    // MusicSource interface (Implementor in Bridge Pattern)
    public interface MusicSource {
        String getSourceInfo();
    }

    // LocalFileSource class (Concrete Implementor)
    public static class LocalFileSource implements MusicSource {
        private String filePath;

        public LocalFileSource(String filePath) {
            this.filePath = filePath;
        }

        @Override
        public String getSourceInfo() {
            return "Local file: " + filePath;
        }
    }

    // OnlineStreamingSource class (Concrete Implementor)
    public static class OnlineStreamingSource implements MusicSource {
        private String url;

        public OnlineStreamingSource(String url) {
            this.url = url;
        }

        @Override
        public String getSourceInfo() {
            return "Online stream: " + url;
        }
    }

    // RadioSource class (Concrete Implementor)
    public static class RadioSource implements MusicSource {
        private String station;

        public RadioSource(String station) {
            this.station = station;
        }

        @Override
        public String getSourceInfo() {
            return "Radio station: " + station;
        }
    }

    // BaseMusicPlayer class (Refined Abstraction)
    public static class BaseMusicPlayer implements MusicPlayer {
        protected MusicSource musicSource;

        public BaseMusicPlayer(MusicSource musicSource) {
            this.musicSource = musicSource;
        }

        @Override
        public void play() {
            System.out.println("Playing " + musicSource.getSourceInfo());
        }

        @Override
        public void stop() {
            System.out.println("Stopped " + musicSource.getSourceInfo());
        }

        @Override
        public void pause() {
            System.out.println("Paused " + musicSource.getSourceInfo());
        }
    }

    // MusicPlayerDecorator abstract class (Decorator Pattern)
    public abstract static class MusicPlayerDecorator implements MusicPlayer {
        protected MusicPlayer musicPlayer;

        public MusicPlayerDecorator(MusicPlayer musicPlayer) {
            this.musicPlayer = musicPlayer;
        }

        @Override
        public void play() {
            musicPlayer.play();
        }

        @Override
        public void stop() {
            musicPlayer.stop();
        }

        @Override
        public void pause() {
            musicPlayer.pause();
        }
    }

    // EqualizerDecorator class
    public static class EqualizerDecorator extends MusicPlayerDecorator {
        public EqualizerDecorator(MusicPlayer musicPlayer) {
            super(musicPlayer);
        }

        public void setEqualizer(String setting) {
            System.out.println("Setting equalizer to: " + setting);
        }

        @Override
        public void play() {
            super.play();
            setEqualizer("Flat");
        }
    }

    // VolumeControlDecorator class
    public static class VolumeControlDecorator extends MusicPlayerDecorator {
        private int volume;

        public VolumeControlDecorator(MusicPlayer musicPlayer, int volume) {
            super(musicPlayer);
            this.volume = volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
            System.out.println("Volume set to: " + volume);
        }

        @Override
        public void play() {
            super.play();
            setVolume(volume);
        }
    }

    // Main method to demonstrate the application
    public static void main(String[] args) {
        // Create music sources
        MusicSource localSource = new LocalFileSource("song.mp3");
        MusicSource onlineSource = new OnlineStreamingSource("http://example.com/stream");
        MusicSource radioSource = new RadioSource("101.1 FM");

        // Create music players for each source
        MusicPlayer localPlayer = new BaseMusicPlayer(localSource);
        MusicPlayer onlinePlayer = new BaseMusicPlayer(onlineSource);
        MusicPlayer radioPlayer = new BaseMusicPlayer(radioSource);

        // Decorate players with additional features
        MusicPlayer equalizedLocalPlayer = new EqualizerDecorator(localPlayer);
        MusicPlayer volumeControlledOnlinePlayer = new VolumeControlDecorator(onlinePlayer, 75);

        // Play different music sources
        equalizedLocalPlayer.play();
        volumeControlledOnlinePlayer.play();
        radioPlayer.play();

        // Demonstrate stopping and pausing
        equalizedLocalPlayer.pause();
        volumeControlledOnlinePlayer.stop();
        radioPlayer.pause();
    }
}
