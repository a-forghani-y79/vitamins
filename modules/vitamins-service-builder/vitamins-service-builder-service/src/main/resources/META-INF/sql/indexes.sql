create index IX_4620929D on FOO_PersistedVitamin (name[$COLUMN_LENGTH:75$]);
create index IX_857C98E5 on FOO_PersistedVitamin (surrogateId[$COLUMN_LENGTH:75$]);
create index IX_664D67D2 on FOO_PersistedVitamin (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_AD51BAD4 on FOO_PersistedVitamin (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_9F61A977 on FOO_VitaminDetail (persistedVitaminId, type_);
create index IX_6E49424 on FOO_VitaminDetail (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_140BD3A6 on FOO_VitaminDetail (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_7774ED78 on NEB_PersistedVitamin (name[$COLUMN_LENGTH:75$]);
create index IX_F51061AA on NEB_PersistedVitamin (surrogateId[$COLUMN_LENGTH:75$]);
create index IX_5401522D on NEB_PersistedVitamin (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C227FEF on NEB_PersistedVitamin (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_7AD52392 on NEB_VitaminDetail (persistedVitaminId, type_);
create index IX_E0A93FA9 on NEB_VitaminDetail (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_839F9C6B on NEB_VitaminDetail (uuid_[$COLUMN_LENGTH:75$], groupId);