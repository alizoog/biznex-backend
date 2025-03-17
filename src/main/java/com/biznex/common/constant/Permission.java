package com.biznex.common.constant;

import lombok.Getter;

@Getter
public enum Permission {

    VIEW_ADMINS("Adminlar ma'lumotlarini ko'rish", "View Admins' Data", "Просмотр данных администраторов"),
    VIEW_ADMIN("Admin ma'lumotlarini ko'rish", "View Admin's Data", "Просмотр данных администратора"),
    CREATE_ADMIN("Admin ma'lumotlarini yaratish", "Create Admin's Data", "Создание данных администратора"),
    UPDATE_ADMIN("Admin ma'lumotlarini tahrirlash", "Update Admin's Data", "Обновление данных администратора"),
    DELETE_ADMIN("Admin ma'lumotlarini o'chirish", "Delete Admin's Data", "Удаление данных администратора"),

    VIEW_USERS("Foydalnuvchilar ma'lumotlarini ko'rish", "View Users' Data", "Просмотр данных пользователей"),
    VIEW_USER("Foydalnuvchi ma'lumotlarini ko'rish", "View User's Data", "Просмотр данных пользователя"),
    CREATE_USER("Foydalnuvchi ma'lumotlarini yaratish", "Create User's Data", "Создание данных пользователя"),
    UPDATE_USER("Foydalnuvchi ma'lumotlarini tahrirlash", "Update User's Data", "Обновление данных пользователя"),
    DELETE_USER("Foydalnuvchi ma'lumotlarini o'chirish", "Delete User's Data", "Удаление данных пользователя"),

    VIEW_ROLES("Role lar ma'lumotlarini ko'rish", "View Roles' Data", "Просмотр данных ролей"),
    VIEW_ROLE("Role ma'lumotlarini ko'rish", "View Role's Data", "Просмотр данных роли"),
    CREATE_ROLE("Role ma'lumotlarini yaratish", "Create Role's Data", "Создание данных роли"),
    UPDATE_ROLE("Role ma'lumotlarini tahrirlash", "Update Role's Data", "Обновление данных роли"),
    DELETE_ROLE("Role ma'lumotlarini o'chirish", "Delete Role's Data", "Удаление данных роли"),

    VIEW_BLOGS("Bloglar ma'lumotlarini ko'rish", "View Blogs' Data", "Просмотр данных блогов"),
    VIEW_BLOG("Blog ma'lumotlarini ko'rish", "View Blog's Data", "Просмотр данных блога"),
    CREATE_BLOG("Blog ma'lumotlarini yaratish", "Create Blog's Data", "Создание данных блога"),
    UPDATE_BLOG("Blog ma'lumotlarini tahrirlash", "Update Blog's Data", "Обновление данных блога"),
    DELETE_BLOG("Blog ma'lumotlarini o'chirish", "Delete Blog's Data", "Удаление данных блога"),

    VIEW_BANNERS("Bannerlar ma'lumotlarini ko'rish", "View Banners' Data", "Просмотр данных баннеров"),
    VIEW_BANNER("Banner ma'lumotlarini ko'rish", "View Banner's Data", "Просмотр данных баннера"),
    CREATE_BANNER("Banner ma'lumotlarini yaratish", "Create Banner's Data", "Создание данных баннера"),
    UPDATE_BANNER("Banner ma'lumotlarini tahrirlash", "Update Banner's Data", "Обновление данных баннера"),
    DELETE_BANNER("Banner ma'lumotlarini o'chirish", "Delete Banner's Data", "Удаление данных баннера"),

    VIEW_NETWORKS("Ijtimoiy tarmoqlar ma'lumotlarini ko'rish", "View Networks' Data", "Просмотр данных социальных сетей"),
    VIEW_NETWORK("Ijtimoiy tarmoq ma'lumotlarini ko'rish", "View Network's Data", "Просмотр данных сети"),
    CREATE_NETWORK("Ijtimoiy tarmoq ma'lumotlarini yaratish", "Create Network's Data", "Создание данных сети"),
    UPDATE_NETWORK("Ijtimoiy tarmoq ma'lumotlarini tahrirlash", "Update Network's Data", "Обновление данных сети"),

    VIEW_TRANSLATIONS("Saytni tarjimalar ma'lumotlarini ko'rish", "View Translations' Data", "Просмотр данных переводов"),
    VIEW_TRANSLATION("Saytni tarjima ma'lumotlarini ko'rish", "View Translation's Data", "Просмотр данных перевода"),
    CREATE_TRANSLATION("Saytni tarjima ma'lumotlarini yaratish", "Create Translation's Data", "Создание данных перевода"),
    UPDATE_TRANSLATION("Saytni tarjima ma'lumotlarini tahrirlash", "Update Translation's Data", "Обновление данных перевода"),
    DELETE_TRANSLATION("Saytni tarjima ma'lumotlarini o'chirish", "Delete Translation's Data", "Удаление данных перевода"),

    VIEW_OPEN_DATAS("Ochiq malumotlar ma'lumotlarini ko'rish", "View Open Data's Data", "Просмотр данных открытых данных"),
    VIEW_OPEN_DATA("Ochiq malumot ma'lumotlarini ko'rish", "View Open Data's Data", "Просмотр данных открытых данных"),
    CREATE_OPEN_DATA("Ochiq malumot ma'lumotlarini yaratish", "Create Open Data's Data", "Создание данных открытых данных"),
    UPDATE_OPEN_DATA("Ochiq malumot ma'lumotlarini tahrirlash", "Update Open Data's Data", "Обновление данных открытых данных"),
    DELETE_OPEN_DATA("Ochiq malumot ma'lumotlarini o'chirish", "Delete Open Data's Data", "Удаление данных открытых данных"),

    VIEW_MANAGEMENTS("Rahbariyat ma'lumotlarini ko'rish", "View Management's Data", "Просмотр данных руководства"),
    VIEW_MANAGEMENT("Rahbar ma'lumotlarini ko'rish", "View Manager's Data", "Просмотр данных менеджера"),
    CREATE_MANAGEMENT("Rahbar ma'lumotlarini yaratish", "Create Manager's Data", "Создание данных менеджера"),
    UPDATE_MANAGEMENT("Rahbar ma'lumotlarini tahrirlash", "Update Manager's Data", "Обновление данных менеджера"),
    DELETE_MANAGEMENT("Rahbar ma'lumotlarini o'chirish", "Delete Manager's Data", "Удаление данных менеджера"),

    VIEW_WORK_ORDERS("Ish tartiblar ma'lumotlarini ko'rish", "View Work Orders' Data", "Просмотр данных рабочих приказов"),
    CREATE_WORK_ORDER("Ish tartib ma'lumotini yaratish", "Create Work Order's Data", "Создание данных рабочего приказа"),

    VIEW_CONTACT_USES("Biz bilan Bog'lanishlar ma'lumotlarini ko'rish", "View Contact Us' Data", "Просмотр данных Контакта с нами"),
    CREATE_CONTACT_US("Biz bilan Bog'lanishlar ma'lumotini yaratish", "Create Contact Us' Data", "Создание данных Контакта с нами"),

    VIEW_CAREERS("Bo'sh ish o'rinlari ma'lumotlarini ko'rish", "View Careers' Data", "Просмотр данных вакансий"),
    VIEW_CAREER("Bo'sh ish o'rin ma'lumotini ko'rish", "View Career's Data", "Просмотр данных карьеры"),
    CREATE_CAREER("Bo'sh ish o'rin ma'lumotini yaratish", "Create Career's Data", "Создание данных вакансии"),
    UPDATE_CAREER("Bo'sh ish o'rin ma'lumotini tahrirlash", "Update Career's Data", "Обновление данных вакансии"),
    DELETE_CAREER("Bo'sh ish o'rin ma'lumotini o'chirish", "Delete Career's Data", "Удаление данных вакансии"),

    VIEW_VIDEOS("Videolar ma'lumotlarini ko'rish", "View video data", "Просмотр данных видео"),
    VIEW_VIDEO("Video ma'lumotlarini ko'rish", "View video information", "Просмотр информации о видео"),
    CREATE_VIDEO("Video ma'lumotlarini yaratish", "Create video data", "Создание данных видео"),
    UPDATE_VIDEO("Video ma'lumotlarini tahrirlash", "Edit video data", "Редактирование данных видео"),
    DELETE_VIDEO("Video ma'lumotlarini o'chirish", "Delete video data", "Удаление данных видео"),

    VIEW_AUDIOS("Audiolar ma'lumotlarini ko'rish", "View audio data", "Просмотр данных аудио"),
    VIEW_AUDIO("Audio ma'lumotlarini ko'rish", "View audio information", "Просмотр информации об аудио"),
    CREATE_AUDIO("Audio ma'lumotlarini yaratish", "Create audio data", "Создание данных аудио"),
    UPDATE_AUDIO("Audio ma'lumotlarini tahrirlash", "Edit audio data", "Редактирование данных аудио"),
    DELETE_AUDIO("Audio ma'lumotlarini o'chirish", "Delete audio data", "Удаление данных аудио"),

    VIEW_PHOTOS("Rasmlar ma'lumotlarini ko'rish", "View photo data", "Просмотр данных изображений"),
    VIEW_PHOTO("Rasm ma'lumotlarini ko'rish", "View photo information", "Просмотр информации об изображении"),
    CREATE_PHOTO("Rasm ma'lumotlarini yaratish", "Create photo data", "Создание данных изображений"),
    UPDATE_PHOTO("Rasm ma'lumotlarini tahrirlash", "Edit photo data", "Редактирование данных изображений"),
    DELETE_PHOTO("Rasm ma'lumotlarini o'chirish", "Delete photo data", "Удаление данных изображений"),

    VIEW_MEDIA_CATEGORIES("Media kategoriyalar ma'lumotlarini ko'rish", "View media categories data", "Просмотр данных медиа-категорий"),
    VIEW_MEDIA_CATEGORY("Media kategoriya ma'lumotlarini ko'rish", "View media category information", "Просмотр информации о медиа-категории"),
    CREATE_MEDIA_CATEGORY("Media kategoriya ma'lumotlarini yaratish", "Create media category", "Создание медиа-категории"),
    UPDATE_MEDIA_CATEGORY("Media kategoriya ma'lumotlarini tahrirlash", "Edit media category", "Редактирование медиа-категории"),
    DELETE_MEDIA_CATEGORY("Media kategoriya ma'lumotlarini o'chirish", "Delete media category", "Удаление медиа-категории");




    private final String titleUz;
    private final String titleEn;
    private final String titleRu;

    Permission(String titleUz, String titleEn, String titleRu) {
        this.titleUz = titleUz;
        this.titleEn = titleEn;
        this.titleRu = titleRu;
    }
}
