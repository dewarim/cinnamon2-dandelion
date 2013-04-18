package cinnamon

class AuditLog {

    static constraints = {
        repository size: 1..255
        hibernateId size: 1..32, nullable: true
        fieldName size: 1..127, nullable: true
        oldValueName size: 1..255, nullable: true
        newValueName size: 1..255, nullable: true
        username size: 1..255, nullable: true
        userId size: 1..20, nullable: true
        className size:1..255, nullable: true
        objectName size:1..4000, nullable: true
        oldValue nullable: true
        newValue nullable: true
        logMessage nullable: true
    }
    
    static mapping = {
        datasource 'audit'
        version false
        table 'audit_log'
        oldValue type: 'text'
        newValue type: 'text'
        logMessage type: 'text'
    }
        
    String repository
    Date dateCreated
    String hibernateId
    String objectName
    String username
    String userId
    String fieldName
    String oldValue
    String oldValueName 
    String newValue
    String newValueName
    String logMessage

    String metadata = '<meta />'
    String className

    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof AuditLog)) return false

        AuditLog auditLog = (AuditLog) o

        if (className != auditLog.className) return false
        if (dateCreated != auditLog.dateCreated) return false
        if (fieldName != auditLog.fieldName) return false
        if (hibernateId != auditLog.hibernateId) return false
        if (metadata != auditLog.metadata) return false
        if (newValue != auditLog.newValue) return false
        if (newValueName != auditLog.newValueName) return false
        if (objectName != auditLog.objectName) return false
        if (oldValue != auditLog.oldValue) return false
        if (oldValueName != auditLog.oldValueName) return false
        if (repository != auditLog.repository) return false
        if (userId != auditLog.userId) return false
        if (username != auditLog.username) return false
        if (logMessage != auditLog.logMessage) return false

        return true
    }

    int hashCode() {
        int result
        result = (repository != null ? repository.hashCode() : 0)
        result = 31 * result + (hibernateId != null ? hibernateId.hashCode() : 0)
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0)
        result = 31 * result + (metadata != null ? metadata.hashCode() : 0)
        result = 31 * result + (className != null ? className.hashCode() : 0)
        return result
    }
}
