public class PerformanceMonitor {
    private int collisionCount;
    private long indexingTime;
    private long totalSearchTime;
    private long minSearchTime;
    private long maxSearchTime;
    private int searchCount;

    public void startIndexing() {
        // Başlangıç zamanını kaydet
        indexingTime = System.currentTimeMillis();
    }

    public void endIndexing() {
        // İndexleme tamamlandığında geçen süreyi hesapla
        indexingTime = System.currentTimeMillis() - indexingTime;
    }

    public void startSearch() {
        // Arama başladığında zamanı kaydet
        searchCount++;
    }

    public void endSearch(long searchTime) {
        // Arama tamamlandığında geçen süreyi hesapla
        totalSearchTime += searchTime;
        minSearchTime = Math.min(minSearchTime, searchTime);
        maxSearchTime = Math.max(maxSearchTime, searchTime);
    }

    public void incrementCollisionCount() {
        // Çakışma sayısını artır
        collisionCount++;
    }

    public void printPerformanceMetrics() {
        // Performans metriklerini yazdır
        System.out.println("Collision Count: " + collisionCount);
        System.out.println("Indexing Time: " + indexingTime + " ms");
        System.out.println("Average Search Time: " + (totalSearchTime / searchCount) + " ms");
        System.out.println("Min Search Time: " + minSearchTime + " ms");
        System.out.println("Max Search Time: " + maxSearchTime + " ms");
    }
}
