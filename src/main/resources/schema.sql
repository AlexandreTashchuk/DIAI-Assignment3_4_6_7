-- Repair legacy owner references created before ownership migration.
-- Safe on clean databases; continue-on-error handles missing column/table during first boot.
UPDATE event
SET owner_id = NULL
WHERE owner_id = 0;

