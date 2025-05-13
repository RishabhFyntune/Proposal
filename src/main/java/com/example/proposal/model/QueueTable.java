package com.example.proposal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Queue")
public class QueueTable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "row_read")
    private Integer rowRead;

    @Column(name = "row_count")
    private Integer rowCount;

    @Column(name = "is_processed")
    private Character isProcessed;

    @Column(name = "status")
    private Character status;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "last_processed_row")
    private Integer lastProcessedRow = 0;

    public QueueTable() {
        super();
    }

    public QueueTable(Long id, Integer rowRead, Integer rowCount, Character isProcessed, Character status, String filePath, Integer lastProcessedRow) {
        this.id = id;
        this.rowRead = rowRead;
        this.rowCount = rowCount;
        this.isProcessed = isProcessed;
        this.status = status;
        this.filePath = filePath;
        this.lastProcessedRow = lastProcessedRow;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRowRead() {
        return rowRead;
    }

    public void setRowRead(Integer rowRead) {
        this.rowRead = rowRead;
    }

    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public Character getIsProcessed() {
        return isProcessed;
    }

    public void setIsProcessed(Character isProcessed) {
        this.isProcessed = isProcessed;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getLastProcessedRow() {
        return lastProcessedRow;
    }

    public void setLastProcessedRow(Integer lastProcessedRow) {
        this.lastProcessedRow = lastProcessedRow;
    }
}
