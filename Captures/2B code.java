// MusicStreamingApp.java
public class MusicStreamingApp {

    // MusicPlayer interface
    public interface MusicPlayer {
        void play();
        void stop();
        void pause();
    }

    // LocalFilePlayer class
    public static class LocalFilePlayer implements MusicPlayer {
        private String filePath;

        public LocalFilePlayer(String filePath) {
            this.filePath = filePath;
        }

        @Override
        public void play() {
            System.out.println("Playing local file: " + filePath);
        }

        @Override
        public void stop() {
            System.out.println("Stopped playing local file: " + filePath);
        }

        @Override
        public void pause() {
            System.out.println("Paused local file: " + filePath);
        }
    }

    // OnlineStreamingPlayer class
    public static class OnlineStreamingPlayer implements MusicPlayer {
        private String url;

        public OnlineStreamingPlayer(String url) {
            this.url = url;
        }

        @Override
        public void play() {
            System.out.println("Playing online stream: " + url);
        }

        @Override
        public void stop() {
            System.out.println("Stopped online stream: " + url);
        }

        @Override
        public void pause() {
            System.out.println("Paused online stream: " + url);
        }
    }

    // RadioPlayer class
    public static class RadioPlayer implements MusicPlayer {
        private String station;

        public RadioPlayer(String station) {
            this.station = station;
        }

        @Override
        public void play() {
            System.out.println("Playing radio station: " + station);
        }

        @Override
        public void stop() {
            System.out.println("Stopped radio station: " + station);
        }

        @Override
        public void pause() {
            System.out.println("Paused radio station: " + station);
        }
    }

    // MusicPlayerDecorator abstract class
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

    // Main method
    public static void main(String[] args) {
        // Create different music players
        MusicPlayer localPlayer = new LocalFilePlayer("song.mp3");
        MusicPlayer onlinePlayer = new OnlineStreamingPlayer("http://example.com/stream");
        MusicPlayer radioPlayer = new RadioPlayer("101.1 FM");

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
