package com.example.data.repository

import com.example.data.model.*

object JapaneseContentRepository {

    // --- KANA DATA ---
    val hiraganaList: List<KanaCharacter> = listOf(
        // Seion
        KanaCharacter("あ", "a", KanaType.HIRAGANA_SEION, "Diucapkan jelas seperti 'a' pada 'ayah'", "あさ (asa)", "pagi", 3),
        KanaCharacter("い", "i", KanaType.HIRAGANA_SEION, "Diucapkan seperti 'i' pada 'ikan'", "いぬ (inu)", "anjing", 2),
        KanaCharacter("う", "u", KanaType.HIRAGANA_SEION, "Bibir tidak terlalu maju, seperti 'u' pada 'untuk'", "うみ (umi)", "laut", 2),
        KanaCharacter("え", "e", KanaType.HIRAGANA_SEION, "Diucapkan seperti 'e' pada 'enak'", "えき (eki)", "stasiun", 2),
        KanaCharacter("お", "o", KanaType.HIRAGANA_SEION, "Diucapkan seperti 'o' pada 'orang'", "おちゃ (ocha)", "teh hijau", 3),

        KanaCharacter("か", "ka", KanaType.HIRAGANA_SEION, "Kombinasi bunyi k dan a", "かさ (kasa)", "payung", 3),
        KanaCharacter("き", "ki", KanaType.HIRAGANA_SEION, "Kombinasi bunyi k dan i", "きのう (kinou)", "kemarin", 4),
        KanaCharacter("く", "ku", KanaType.HIRAGANA_SEION, "Kombinasi bunyi k dan u", "くるま (kuruma)", "mobil", 1),
        KanaCharacter("け", "ke", KanaType.HIRAGANA_SEION, "Kombinasi bunyi k dan e", "けいさつ (keisatsu)", "polisi", 3),
        KanaCharacter("こ", "ko", KanaType.HIRAGANA_SEION, "Kombinasi bunyi k dan o", "こども (kodomo)", "anak-anak", 2),

        KanaCharacter("さ", "sa", KanaType.HIRAGANA_SEION, "Kombinasi bunyi s dan a", "さくら (sakura)", "bunga sakura", 3),
        KanaCharacter("し", "shi", KanaType.HIRAGANA_SEION, "Mirip 'sy' pada 'syarat'", "しごと (shigoto)", "pekerjaan", 1),
        KanaCharacter("す", "su", KanaType.HIRAGANA_SEION, "Kombinasi bunyi s dan u", "すし (sushi)", "sushi", 2),
        KanaCharacter("せ", "se", KanaType.HIRAGANA_SEION, "Kombinasi bunyi s dan e", "せんせい (sensei)", "guru", 3),
        KanaCharacter("そ", "so", KanaType.HIRAGANA_SEION, "Kombinasi bunyi s dan o", "そら (sora)", "langit", 1),

        KanaCharacter("た", "ta", KanaType.HIRAGANA_SEION, "Kombinasi bunyi t dan a", "たべる (taberu)", "makan", 4),
        KanaCharacter("ち", "chi", KanaType.HIRAGANA_SEION, "Mirip 'c' pada 'cinta'", "ちず (chizu)", "peta", 2),
        KanaCharacter("つ", "tsu", KanaType.HIRAGANA_SEION, "Ucapkan 'ts' seperti pada 'tsunami'", "つき (tsuki)", "bulan", 1),
        KanaCharacter("て", "te", KanaType.HIRAGANA_SEION, "Kombinasi bunyi t dan e", "てがみ (tegami)", "surat", 1),
        KanaCharacter("と", "to", KanaType.HIRAGANA_SEION, "Kombinasi bunyi t dan o", "ともだち (tomodachi)", "teman", 2),

        KanaCharacter("な", "na", KanaType.HIRAGANA_SEION, "Kombinasi bunyi n dan a", "なつ (natsu)", "musim panas", 4),
        KanaCharacter("に", "ni", KanaType.HIRAGANA_SEION, "Kombinasi bunyi n dan i", "にほん (nihon)", "Jepang", 3),
        KanaCharacter("ぬ", "nu", KanaType.HIRAGANA_SEION, "Kombinasi bunyi n dan u", "ぬいぐるみ (nuigurumi)", "boneka", 2),
        KanaCharacter("ね", "ne", KanaType.HIRAGANA_SEION, "Kombinasi bunyi n dan e", "ねこ (neko)", "kucing", 2),
        KanaCharacter("の", "no", KanaType.HIRAGANA_SEION, "Kombinasi bunyi n dan o", "のみもの (nomimono)", "minuman", 1),

        KanaCharacter("は", "ha", KanaType.HIRAGANA_SEION, "Kombinasi bunyi h dan a", "はな (hana)", "bunga / hidung", 3),
        KanaCharacter("ひ", "hi", KanaType.HIRAGANA_SEION, "Kombinasi bunyi h dan i", "ひかり (hikari)", "cahaya", 1),
        KanaCharacter("ふ", "fu", KanaType.HIRAGANA_SEION, "Hembuskan nafas di antara kedua bibir", "ふね (fune)", "kapal", 4),
        KanaCharacter("へ", "he", KanaType.HIRAGANA_SEION, "Kombinasi bunyi h dan e", "へや (heya)", "kamar", 1),
        KanaCharacter("ほ", "ho", KanaType.HIRAGANA_SEION, "Kombinasi bunyi h dan o", "ほん (hon)", "buku", 4),

        KanaCharacter("ま", "ma", KanaType.HIRAGANA_SEION, "Kombinasi bunyi m dan a", "まち (machi)", "kota", 3),
        KanaCharacter("み", "mi", KanaType.HIRAGANA_SEION, "Kombinasi bunyi m dan i", "みず (mizu)", "air", 2),
        KanaCharacter("む", "mu", KanaType.HIRAGANA_SEION, "Kombinasi bunyi m dan u", "むし (mushi)", "serangga", 3),
        KanaCharacter("め", "me", KanaType.HIRAGANA_SEION, "Kombinasi bunyi m dan e", "め (me)", "mata", 2),
        KanaCharacter("も", "mo", KanaType.HIRAGANA_SEION, "Kombinasi bunyi m dan o", "もり (mori)", "hutan", 3),

        KanaCharacter("や", "ya", KanaType.HIRAGANA_SEION, "Kombinasi bunyi y dan a", "やま (yama)", "gunung", 3),
        KanaCharacter("ゆ", "yu", KanaType.HIRAGANA_SEION, "Kombinasi bunyi y dan u", "ゆき (yuki)", "salju", 2),
        KanaCharacter("よ", "yo", KanaType.HIRAGANA_SEION, "Kombinasi bunyi y dan o", "よる (yoru)", "malam", 2),

        KanaCharacter("ら", "ra", KanaType.HIRAGANA_SEION, "Lidah menyentuh langit-langit (antara r dan l)", "らいしゅう (raishuu)", "minggu depan", 2),
        KanaCharacter("り", "ri", KanaType.HIRAGANA_SEION, "Antara bunyi r dan l", "りんご (ringo)", "apel", 2),
        KanaCharacter("る", "ru", KanaType.HIRAGANA_SEION, "Antara bunyi r dan l", "るす (rusu)", "sedang keluar rumah", 1),
        KanaCharacter("れ", "re", KanaType.HIRAGANA_SEION, "Antara bunyi r dan l", "れきし (rekishi)", "sejarah", 2),
        KanaCharacter("ろ", "ro", KanaType.HIRAGANA_SEION, "Antara bunyi r dan l", "ろうそく (rousoku)", "lilin", 1),

        KanaCharacter("わ", "wa", KanaType.HIRAGANA_SEION, "Kombinasi bunyi w dan a", "わたし (watashi)", "saya", 2),
        KanaCharacter("を", "wo/o", KanaType.HIRAGANA_SEION, "Digunakan khusus sebagai partikel objek 'o'", "ほんを (hon o)", "(membaca) buku", 3),
        KanaCharacter("ん", "n", KanaType.HIRAGANA_SEION, "Bunyi nasal konsonan di akhir kata", "にほん (nihon)", "Jepang", 1),

        // Dakuten & Handakuten
        KanaCharacter("が", "ga", KanaType.HIRAGANA_DAKUTEN, "Huruf か dengan tanda tenten", "がっこう (gakkou)", "sekolah", 5),
        KanaCharacter("ぎ", "gi", KanaType.HIRAGANA_DAKUTEN, "Huruf き dengan tenten", "ぎんこう (ginkou)", "bank", 6),
        KanaCharacter("ぐ", "gu", KanaType.HIRAGANA_DAKUTEN, "Huruf く dengan tenten", "ぐんたい (guntai)", "militer", 3),
        KanaCharacter("げ", "ge", KanaType.HIRAGANA_DAKUTEN, "Huruf け dengan tenten", "げんき (genki)", "sehat / semangat", 5),
        KanaCharacter("ご", "go", KanaType.HIRAGANA_DAKUTEN, "Huruf こ dengan tenten", "ごはん (gohan)", "nasi / makan", 4),

        KanaCharacter("ざ", "za", KanaType.HIRAGANA_DAKUTEN, "Huruf さ dengan tenten", "ざっし (zasshi)", "majalah", 5),
        KanaCharacter("じ", "ji", KanaType.HIRAGANA_DAKUTEN, "Huruf し dengan tenten", "じかん (jikan)", "waktu", 3),
        KanaCharacter("ず", "zu", KanaType.HIRAGANA_DAKUTEN, "Huruf す dengan tenten", "みず (mizu)", "air", 4),
        KanaCharacter("ぜ", "ze", KanaType.HIRAGANA_DAKUTEN, "Huruf せ dengan tenten", "ぜんぶ (zenbu)", "semuanya", 5),
        KanaCharacter("ぞ", "zo", KanaType.HIRAGANA_DAKUTEN, "Huruf そ dengan tenten", "ぞう (zou)", "gajah", 3),

        KanaCharacter("だ", "da", KanaType.HIRAGANA_DAKUTEN, "Huruf た dengan tenten", "だいがく (daigaku)", "universitas", 6),
        KanaCharacter("ぢ", "ji/dji", KanaType.HIRAGANA_DAKUTEN, "Huruf ち dengan tenten", "はなぢ (hanaji)", "mimisan", 4),
        KanaCharacter("づ", "zu/dzu", KanaType.HIRAGANA_DAKUTEN, "Huruf つ dengan tenten", "つづく (tsuzuku)", "berlanjut", 3),
        KanaCharacter("で", "de", KanaType.HIRAGANA_DAKUTEN, "Huruf て dengan tenten", "でんしゃ (densha)", "kereta listrik", 3),
        KanaCharacter("ど", "do", KanaType.HIRAGANA_DAKUTEN, "Huruf と dengan tenten", "どこ (doko)", "di mana", 4),

        KanaCharacter("ば", "ba", KanaType.HIRAGANA_DAKUTEN, "Huruf は dengan tenten", "ばす (basu)", "bus", 5),
        KanaCharacter("び", "bi", KanaType.HIRAGANA_DAKUTEN, "Huruf ひ dengan tenten", "びょういん (byouin)", "rumah sakit", 3),
        KanaCharacter("ぶ", "bu", KanaType.HIRAGANA_DAKUTEN, "Huruf ふ dengan tenten", "ぶた (buta)", "babi", 6),
        KanaCharacter("べ", "be", KanaType.HIRAGANA_DAKUTEN, "Huruf へ dengan tenten", "べんきょう (benkyou)", "belajar", 3),
        KanaCharacter("ぼ", "bo", KanaType.HIRAGANA_DAKUTEN, "Huruf ほ dengan tenten", "ぼうし (boushi)", "topi", 6),

        KanaCharacter("ぱ", "pa", KanaType.HIRAGANA_DAKUTEN, "Huruf は dengan maru (lingkaran)", "ぱん (pan)", "roti", 4),
        KanaCharacter("ぴ", "pi", KanaType.HIRAGANA_DAKUTEN, "Huruf ひ dengan maru", "ぴあの (piano)", "piano", 2),
        KanaCharacter("ぷ", "pu", KanaType.HIRAGANA_DAKUTEN, "Huruf ふ dengan maru", "ぷーる (puuru)", "kolam renang", 5),
        KanaCharacter("ぺ", "pe", KanaType.HIRAGANA_DAKUTEN, "Huruf へ dengan maru", "ぺん (pen)", "pena / pulpen", 2),
        KanaCharacter("ぽ", "po", KanaType.HIRAGANA_DAKUTEN, "Huruf ほ dengan maru", "ぽすと (posuto)", "kotak pos", 5),

        // Yoon (Gabungan)
        KanaCharacter("きゃ", "kya", KanaType.HIRAGANA_YOON, "き + ゃ kecil", "きゃく (kyaku)", "tamu / pelanggan", 5),
        KanaCharacter("きゅ", "kyu", KanaType.HIRAGANA_YOON, "き + ゅ kecil", "きゅうり (kyuuri)", "mentimun", 5),
        KanaCharacter("きょ", "kyo", KanaType.HIRAGANA_YOON, "き + ょ kecil", "きょう (kyou)", "hari ini", 5),

        KanaCharacter("しゃ", "sha", KanaType.HIRAGANA_YOON, "し + ゃ kecil", "しゃしん (shashin)", "foto", 4),
        KanaCharacter("しゅ", "shu", KanaType.HIRAGANA_YOON, "し + ゅ kecil", "しゅくだい (shukudai)", "pekerjaan rumah (PR)", 4),
        KanaCharacter("しょ", "sho", KanaType.HIRAGANA_YOON, "し + ょ kecil", "しょくどう (shokudou)", "kantin", 4),

        KanaCharacter("ちゃ", "cha", KanaType.HIRAGANA_YOON, "ち + ゃ kecil", "おちゃ (ocha)", "teh jepang", 4),
        KanaCharacter("ちゅ", "chu", KanaType.HIRAGANA_YOON, "ち + ゅ kecil", "ちゅうごく (chuugoku)", "Tiongkok", 4),
        KanaCharacter("ちょ", "cho", KanaType.HIRAGANA_YOON, "ち + ょ kecil", "ちょっと (chotto)", "sebentar / sedikit", 4),

        KanaCharacter("りゃ", "rya", KanaType.HIRAGANA_YOON, "り + ゃ kecil", "りゃく (ryaku)", "singkatan", 4),
        KanaCharacter("りゅ", "ryu", KanaType.HIRAGANA_YOON, "り + ゅ kecil", "りゅうがくせい (ryuugakusei)", "mahasiswa asing", 4),
        KanaCharacter("りょ", "ryo", KanaType.HIRAGANA_YOON, "り + ょ kecil", "りょこう (ryokou)", "liburan / traveling", 4)
    )

    val katakanaList: List<KanaCharacter> = listOf(
        KanaCharacter("ア", "a", KanaType.KATAKANA_SEION, "Bentuk sudut tegas untuk kata serapan", "アメリカ (amerika)", "Amerika", 2),
        KanaCharacter("イ", "i", KanaType.KATAKANA_SEION, "Dua goresan sederhana", "インドネシア (indoneshia)", "Indonesia", 2),
        KanaCharacter("ウ", "u", KanaType.KATAKANA_SEION, "Goresan atas mendatar diikuti lengkung", "ウェブ (webu)", "web", 3),
        KanaCharacter("エ", "e", KanaType.KATAKANA_SEION, "Bentuk menyerupai huruf I kapital", "エアコン (eakon)", "AC", 3),
        KanaCharacter("オ", "o", KanaType.KATAKANA_SEION, "Tiga goresan rapi", "オレンジ (orenji)", "jeruk / oranye", 3),

        KanaCharacter("カ", "ka", KanaType.KATAKANA_SEION, "Sangat mirip huruf Hiragana か tanpa titik", "カメラ (kamera)", "kamera", 2),
        KanaCharacter("キ", "ki", KanaType.KATAKANA_SEION, "Mirip garis paralel dengan garis miring", "キッチン (kicchin)", "dapur", 3),
        KanaCharacter("ク", "ku", KanaType.KATAKANA_SEION, "Dua goresan tegas", "クラス (kurasu)", "kelas", 2),
        KanaCharacter("ケ", "ke", KanaType.KATAKANA_SEION, "Garis kiri miring dan kait kanan", "ケーキ (keeki)", "kue", 3),
        KanaCharacter("コ", "ko", KanaType.KATAKANA_SEION, "Membentuk sudut persegi", "コーヒー (koohii)", "kopi", 2),

        KanaCharacter("サ", "sa", KanaType.KATAKANA_SEION, "Garis horizontal lalu dua vertikal", "サラダ (sarada)", "salad", 3),
        KanaCharacter("シ", "shi", KanaType.KATAKANA_SEION, "Tiga titik dari bawah ke atas", "シャツ (shatsu)", "kemeja", 3),
        KanaCharacter("ス", "su", KanaType.KATAKANA_SEION, "Sudut kanan lalu garis miring bawah", "スポーツ (supootsu)", "olahraga", 2),
        KanaCharacter("セ", "se", KanaType.KATAKANA_SEION, "Dua garis sudut", "センター (sentaa)", "pusat / center", 2),
        KanaCharacter("ソ", "so", KanaType.KATAKANA_SEION, "Titik atas dan garis miring ke bawah", "ソファ (sofa)", "sofa", 2),

        KanaCharacter("タ", "ta", KanaType.KATAKANA_SEION, "Mirip kanji 夕", "タクシー (takushii)", "taksi", 3),
        KanaCharacter("チ", "chi", KanaType.KATAKANA_SEION, "Tiga goresan tegas", "チケット (chiketto)", "tiket", 3),
        KanaCharacter("ツ", "tsu", KanaType.KATAKANA_SEION, "Dua titik atas dan garis dari kanan atas ke bawah", "ツアー (tsuaa)", "tur", 3),
        KanaCharacter("テ", "te", KanaType.KATAKANA_SEION, "Dua garis horizontal dan garis miring", "テレビ (terebi)", "televisi", 3),
        KanaCharacter("ト", "to", KanaType.KATAKANA_SEION, "Garis vertikal lalu cabang kanan", "トイレ (toire)", "toilet", 2),

        KanaCharacter("ナ", "na", KanaType.KATAKANA_SEION, "Garis horizontal lalu vertikal melengkung", "ナイフ (naifu)", "pisau", 2),
        KanaCharacter("ニ", "ni", KanaType.KATAKANA_SEION, "Dua garis horizontal sejajar", "ニュース (nyuusu)", "berita", 2),
        KanaCharacter("ヌ", "nu", KanaType.KATAKANA_SEION, "Dua goresan bersilangan", "ヌードル (nuudoru)", "mi / mie", 2),
        KanaCharacter("ネ", "ne", KanaType.KATAKANA_SEION, "Garis atas dan bentuk tiga cabang", "ネクタイ (nekutai)", "dasi", 4),
        KanaCharacter("ノ", "no", KanaType.KATAKANA_SEION, "Satu goresan melengkung ke kiri bawah", "ノート (nooto)", "buku catatan", 1),

        KanaCharacter("ハ", "ha", KanaType.KATAKANA_SEION, "Dua garis miring simetris", "ハンバーガー (hanbaagaa)", "hamburger", 2),
        KanaCharacter("ヒ", "hi", KanaType.KATAKANA_SEION, "Garis horizontal dan sudut melengkung", "ホテル (hoteru)", "hotel", 2),
        KanaCharacter("フ", "fu", KanaType.KATAKANA_SEION, "Satu garis sudut tunggal", "フォーク (fooku)", "garpu", 1),
        KanaCharacter("ヘ", "he", KanaType.KATAKANA_SEION, "Identik dengan Hiragana へ", "ヘリコプター (herikoputaa)", "helikopter", 1),
        KanaCharacter("ホ", "ho", KanaType.KATAKANA_SEION, "Garis horizontal, vertikal, dan dua sayap", "ホーム (hoomu)", "peron stasiun", 4),

        KanaCharacter("マ", "ma", KanaType.KATAKANA_SEION, "Dua goresan sederhana", "マスク (masuku)", "masker", 2),
        KanaCharacter("ミ", "mi", KanaType.KATAKANA_SEION, "Tiga garis miring sejajar", "ミルク (miruku)", "susu", 3),
        KanaCharacter("ム", "mu", KanaType.KATAKANA_SEION, "Membentuk segitiga terbuka", "ムービー (muubii)", "film / movie", 2),
        KanaCharacter("メ", "me", KanaType.KATAKANA_SEION, "Dua goresan bersilang", "メニュー (menyuu)", "menu", 2),
        KanaCharacter("モ", "mo", KanaType.KATAKANA_SEION, "Dua horizontal dan satu vertikal kait", "モデル (moderu)", "model", 3),

        KanaCharacter("ヤ", "ya", KanaType.KATAKANA_SEION, "Bentuk mirip hiragana や", "ヤング (yangu)", "muda", 2),
        KanaCharacter("ユ", "yu", KanaType.KATAKANA_SEION, "Sudut persegi dan garis bawah", "ユーザー (yuuzaa)", "pengguna", 2),
        KanaCharacter("ヨ", "yo", KanaType.KATAKANA_SEION, "Tiga garis mendatar dengan punggung tegak", "ヨーロッパ (yooroppa)", "Eropa", 3),

        KanaCharacter("ラ", "ra", KanaType.KATAKANA_SEION, "Garis atas dan sudut bawah", "ラジオ (rajio)", "radio", 2),
        KanaCharacter("リ", "ri", KanaType.KATAKANA_SEION, "Dua garis vertikal tegak", "リモコン (rimokon)", "remote control", 2),
        KanaCharacter("ル", "ru", KanaType.KATAKANA_SEION, "Dua garis kaki terbuka", "ルール (ruuru)", "aturan", 2),
        KanaCharacter("レ", "re", KanaType.KATAKANA_SEION, "Satu garis miring dengan kait kanan atas", "レストラン (resutoran)", "restoran", 1),
        KanaCharacter("ロ", "ro", KanaType.KATAKANA_SEION, "Kotak persegi sempurna", "ロボット (robotto)", "robot", 3),

        KanaCharacter("ワ", "wa", KanaType.KATAKANA_SEION, "Sudut tegas mirip フ terbuka", "ワイン (wain)", "anggur / wine", 2),
        KanaCharacter("ヲ", "wo", KanaType.KATAKANA_SEION, "Jarang dipakai pada kata serapan", "ヲ (wo)", "partikel", 3),
        KanaCharacter("ン", "n", KanaType.KATAKANA_SEION, "Dua goresan dari kiri bawah naik ke kanan atas", "パン (pan)", "roti", 2)
    )

    // --- VOCABULARY DATA ---
    val vocabularyList: List<VocabularyItem> = listOf(
        // Angka (Numbers)
        VocabularyItem("v1", "一", "いち", "ichi", "Satu (1)", "りんごを一つください。", "りんごをひとつください。", "Ringo o hitotsu kudasai.", "Tolong beri saya satu apel.", JlptLevel.N5, "Angka"),
        VocabularyItem("v2", "二", "に", "ni", "Dua (2)", "猫が二匹います。", "ねこがにひきいます。", "Neko ga nihiki imasu.", "Ada dua ekor kucing.", JlptLevel.N5, "Angka"),
        VocabularyItem("v3", "三", "さん", "san", "Tiga (3)", "三時に会いましょう。", "さんじにあいましょう。", "Sanji ni aimashou.", "Mari bertemu jam tiga.", JlptLevel.N5, "Angka"),
        VocabularyItem("v4", "百", "ひゃく", "hyaku", "Seratus (100)", "これは百円です。", "これはひゃくえんです。", "Kore wa hyaku-en desu.", "Ini harganya seratus yen.", JlptLevel.N5, "Angka"),
        VocabularyItem("v5", "千", "せん", "sen", "Seribu (1000)", "千円札を両替できますか。", "せんえんさつをりょうがえできますか。", "Sen-en satsu o ryougae dekimasu ka.", "Bisakah saya menukar uang kertas seribu yen?", JlptLevel.N5, "Angka"),

        // Waktu & Tanggal
        VocabularyItem("v6", "今日", "きょう", "kyou", "Hari ini", "今日はとてもいい天気ですね。", "きょうはとてもいいてんきですね。", "Kyou wa totemo ii tenki desu ne.", "Hari ini cuacanya sangat bagus ya.", JlptLevel.N5, "Waktu & Tanggal"),
        VocabularyItem("v7", "明日", "あした", "ashita", "Besok", "明日は休みです。", "あしたはやすみです。", "Ashita wa yasumi desu.", "Besok adalah hari libur.", JlptLevel.N5, "Waktu & Tanggal"),
        VocabularyItem("v8", "昨日", "きのう", "kinou", "Kemarin", "昨日は何をしましたか。", "きのうはなにをしましたか。", "Kinou wa nani o shimashita ka.", "Kemarin kamu melakukan apa?", JlptLevel.N5, "Waktu & Tanggal"),
        VocabularyItem("v9", "今", "いま", "ima", "Sekarang", "今、何時ですか。", "いま、なんじですか。", "Ima, nanji desu ka.", "Sekarang jam berapa?", JlptLevel.N5, "Waktu & Tanggal"),
        VocabularyItem("v10", "朝", "あさ", "asa", "Pagi hari", "毎朝六時に起きます。", "まいあさろくじにおきます。", "Maiasa rokuji ni okimasu.", "Setiap pagi saya bangun jam 6.", JlptLevel.N5, "Waktu & Tanggal"),

        // Keluarga (Family)
        VocabularyItem("v11", "家族", "かぞく", "kazoku", "Keluarga", "家族はインドネシアにいます。", "かぞくはいんどねしあにいます。", "Kazoku wa indoneshia ni imasu.", "Keluarga saya berada di Indonesia.", JlptLevel.N5, "Keluarga"),
        VocabularyItem("v12", "父", "ちち", "chichi", "Ayah (sendiri)", "父は会社員です。", "ちちはかいしゃいんです。", "Chichi wa kaishain desu.", "Ayah saya adalah seorang pegawai kantor.", JlptLevel.N5, "Keluarga"),
        VocabularyItem("v13", "母", "はは", "haha", "Ibu (sendiri)", "母の料理はとても美味しいです。", "ははのりょうりはとてもおいしいです。", "Haha no ryouri wa totemo oishii desu.", "Masakan ibu saya sangat lezat.", JlptLevel.N5, "Keluarga"),
        VocabularyItem("v14", "友達", "ともだち", "tomodachi", "Teman / Sahabat", "週末に友達と遊びます。", "しゅうまつにともだちとあそびます。", "Shuumatsu ni tomodachi to asobimasu.", "Di akhir pekan saya bermain bersama teman.", JlptLevel.N5, "Keluarga"),

        // Makanan & Minuman
        VocabularyItem("v15", "ご飯", "ごはん", "gohan", "Nasi / Makanan pokok", "朝ご飯を食べましたか。", "あさごはんをたべましたか。", "Asagohan o tabemashita ka.", "Apakah kamu sudah sarapan?", JlptLevel.N5, "Makanan & Minuman"),
        VocabularyItem("v16", "水", "みず", "mizu", "Air putih", "お水を一杯ください。", "おみずをいっぱいください。", "Omizu o ippai kudasai.", "Minta air putih satu gelas.", JlptLevel.N5, "Makanan & Minuman"),
        VocabularyItem("v17", "魚", "さかな", "sakana", "Ikan", "日本の魚は新鮮です。", "にほんのさかなはしんせんです。", "Nihon no sakana wa shinsen desu.", "Ikan di Jepang sangat segar.", JlptLevel.N5, "Makanan & Minuman"),
        VocabularyItem("v18", "お茶", "おちゃ", "ocha", "Teh hijau Jepang", "温かいお茶を飲みます。", "あたたかいおちゃをのみます。", "Atatakai ocha o nomimasu.", "Saya minum teh hijau hangat.", JlptLevel.N5, "Makanan & Minuman"),

        // Tempat
        VocabularyItem("v19", "駅", "えき", "eki", "Stasiun kereta", "駅の前にコンビニがあります。", "えきのまえにこんびにがあります。", "Eki no mae ni konbini ga arimasu.", "Di depan stasiun ada minimarket.", JlptLevel.N5, "Tempat"),
        VocabularyItem("v20", "学校", "がっこう", "gakkou", "Sekolah", "学校まで歩いて行きます。", "がっこうまであるいていきます。", "Gakkou made aruite ikimasu.", "Saya pergi ke sekolah dengan berjalan kaki.", JlptLevel.N5, "Tempat"),
        VocabularyItem("v21", "部屋", "へや", "heya", "Kamar / Ruangan", "私の部屋は静かです。", "わたしのへやはしずかです。", "Watashi no heya wa shizuka desu.", "Kamar saya tenang dan sunyi.", JlptLevel.N5, "Tempat"),
        VocabularyItem("v22", "病院", "びょういん", "byouin", "Rumah sakit", "風邪をひいたので病院に行きます。", "かぜをひいたのでびょういんにいきます。", "Kaze o hiita node byouin ni ikimasu.", "Karena masuk angin, saya pergi ke rumah sakit.", JlptLevel.N5, "Tempat"),

        // Transportasi
        VocabularyItem("v23", "電車", "でんしゃ", "densha", "Kereta listrik", "電車で東京へ行きます。", "でんしゃでとうきょうへいきます。", "Densha de Toukyou e ikimasu.", "Saya pergi ke Tokyo naik kereta.", JlptLevel.N5, "Transportasi"),
        VocabularyItem("v24", "車", "くるま", "kuruma", "Mobil", "父は新しい車を買いました。", "ちちはあたらしいくるまをかいました。", "Chichi wa atarashii kuruma o kaimashita.", "Ayah membeli mobil baru.", JlptLevel.N5, "Transportasi"),
        VocabularyItem("v25", "自転車", "じてんしゃ", "jitensha", "Sepeda", "駅まで自転車で行きます。", "えきまでじてんしゃでいきます。", "Eki made jitensha de ikimasu.", "Saya pergi ke stasiun naik sepeda.", JlptLevel.N5, "Transportasi"),

        // Pekerjaan & Bisnis
        VocabularyItem("v26", "仕事", "しごと", "shigoto", "Pekerjaan / Kerja", "今日の仕事は終わりました。", "きょうのしごとはおわりました。", "Kyou no shigoto wa owarimashita.", "Pekerjaan hari ini sudah selesai.", JlptLevel.N5, "Pekerjaan"),
        VocabularyItem("v27", "会社", "かいしゃ", "kaisha", "Perusahaan / Kantor", "八時に会社に着きます。", "はちじにかいしゃにつきます。", "Hachiji ni kaisha ni tsukimasu.", "Saya sampai di kantor jam 8.", JlptLevel.N5, "Pekerjaan"),
        VocabularyItem("v28", "先生", "せんせい", "sensei", "Guru / Instruktur / Dokter", "日本語の先生は親切です。", "にほんごのせんせいはしんせつです。", "Nihongo no sensei wa shinsetsu desu.", "Guru bahasa Jepang sangat ramah.", JlptLevel.N5, "Pekerjaan"),

        // Aktivitas Sehari-hari
        VocabularyItem("v29", "勉強", "べんきょう", "benkyou", "Belajar", "毎日一時間日本語を勉強します。", "まいにちいちじかんにほんごをべんきょうします。", "Mainichi ichijikan nihongo o benkyou shimasu.", "Setiap hari saya belajar bahasa Jepang 1 jam.", JlptLevel.N5, "Aktivitas"),
        VocabularyItem("v30", "買い物", "かいもの", "kaimono", "Belanja", "スーパーで買い物をします。", "すーぱーでかいものをします。", "Suupaa de kaimono o shimasu.", "Saya berbelanja di supermarket.", JlptLevel.N5, "Aktivitas"),

        // Kata Kerja Umum
        VocabularyItem("v31", "行く", "いく", "iku", "Pergi", "明日日本へ行きます。", "あしたにほんへいきます。", "Ashita Nihon e ikimasu.", "Besok saya pergi ke Jepang.", JlptLevel.N5, "Kata Kerja"),
        VocabularyItem("v32", "食べる", "たべる", "taberu", "Makan", "ラーメンを食べたいです。", "らーめんをたべたいです。", "Raamen o tabetai desu.", "Saya ingin makan ramen.", JlptLevel.N5, "Kata Kerja"),
        VocabularyItem("v33", "飲む", "のむ", "nomu", "Minum", "冷たい水を飲みます。", "つめたいみずをのみます。", "Tsumetai mizu o nomimasu.", "Saya minum air dingin.", JlptLevel.N5, "Kata Kerja"),
        VocabularyItem("v34", "見る", "みる", "miru", "Melihat / Menonton", "アニメを見ました。", "あにめをみました。", "Anime o mimashita.", "Saya sudah menonton anime.", JlptLevel.N5, "Kata Kerja"),
        VocabularyItem("v35", "話す", "はなす", "hanasu", "Berbicara", "日本語で話しましょう。", "にほんごではなしましょう。", "Nihongo de hanashimashou.", "Mari berbicara dalam bahasa Jepang.", JlptLevel.N5, "Kata Kerja"),

        // Kata Sifat
        VocabularyItem("v36", "大きい", "おおきい", "ookii", "Besar", "この部屋はとても大きいです。", "このへやはとてもおおきいです。", "Kono heya wa totemo ookii desu.", "Kamar ini sangat besar.", JlptLevel.N5, "Kata Sifat"),
        VocabularyItem("v37", "小さい", "ちいさい", "chiisai", "Kecil", "小さい猫がいます。", "ちいさいねこがいます。", "Chiisai neko ga imasu.", "Ada seekor kucing kecil.", JlptLevel.N5, "Kata Sifat"),
        VocabularyItem("v38", "美味しい", "おいしい", "oishii", "Enak / Lezat", "この寿司は美味しいです。", "このすしはおいしいです。", "Kono sushi wa oishii desu.", "Sushi ini sangat enak.", JlptLevel.N5, "Kata Sifat"),
        VocabularyItem("v39", "綺麗", "きれい", "kirei", "Cantik / Bersih / Indah", "富士山はとても綺麗です。", "ふじさんはとてもつれいです。", "Fujisan wa totemo kirei desu.", "Gunung Fuji sangat indah.", JlptLevel.N5, "Kata Sifat"),

        // Ungkapan Praktis
        VocabularyItem("v40", "ありがとうございます", "ありがとうございます", "arigatou gozaimasu", "Terima kasih banyak (sopan)", "ご親切にありがとうございます。", "ごしんせつにありがとうございます。", "Goshinsetsu ni arigatou gozaimasu.", "Terima kasih banyak atas kebaikan Anda.", JlptLevel.N5, "Ungkapan Praktis"),
        VocabularyItem("v41", "すみません", "すみません", "sumimasen", "Permisi / Maaf / Tolong", "すみません、トイレはどこですか。", "すみません、といれはどこですか。", "Sumimasen, toire wa doko desu ka.", "Permisi, toilet ada di sebelah mana ya?", JlptLevel.N5, "Ungkapan Praktis"),
        VocabularyItem("v42", "初めまして", "はじめまして", "hajimemashite", "Salam kenal (pertama bertemu)", "初めまして、リキと申します。", "はじめまして、りきともうします。", "Hajimemashite, Riki to moushimasu.", "Salam kenal, nama saya Riki.", JlptLevel.N5, "Ungkapan Praktis"),

        // JLPT N4 Vocabulary Sample
        VocabularyItem("v43", "準備", "じゅんび", "junbi", "Persiapan / Bersiap-siap", "旅行の準備をします。", "りょこうのじゅんびをします。", "Ryokou no junbi o shimasu.", "Saya sedang mempersiapkan liburan.", JlptLevel.N4, "Pekerjaan", "N4"),
        VocabularyItem("v44", "連絡", "れんらく", "renraku", "Menghubungi / Mengabari", "後でメールで連絡します。", "あとでめーるでれんらくします。", "Ato de meeru de renraku shimasu.", "Nanti saya kabari lewat email.", JlptLevel.N4, "Pekerjaan", "N4"),
        VocabularyItem("v45", "案内", "あんない", "annai", "Memandu / Informasi petunjuk", "東京の街を案内します。", "とうきょうのまちをあんないします。", "Toukyou no machi o annai shimasu.", "Saya akan memandu Anda berkeliling kota Tokyo.", JlptLevel.N4, "Aktivitas", "N4")
    )

    // --- KANJI DATA ---
    val kanjiList: List<KanjiItem> = listOf(
        KanjiItem(
            id = "k1",
            character = "日",
            meaningId = "Matahari, Hari, Jepang",
            onyomi = "ニチ, ジツ",
            kunyomi = "ひ, -か",
            strokeCount = 4,
            jlptLevel = JlptLevel.N5,
            examples = listOf(
                KanjiCompound("日本", "にほん (nihon)", "Jepang"),
                KanjiCompound("日曜日", "にちようび (nichiyoubi)", "Hari Minggu"),
                KanjiCompound("休日", "きゅうじつ (kyuujitsu)", "Hari libur")
            ),
            exampleSentenceJp = "日本は美しい国です。",
            exampleSentenceFurigana = "にほんはうつくしいくにです。",
            exampleSentenceId = "Jepang adalah negara yang indah."
        ),
        KanjiItem(
            id = "k2",
            character = "月",
            meaningId = "Bulan, Hari Senin",
            onyomi = "ゲツ, ガツ",
            kunyomi = "つき",
            strokeCount = 4,
            jlptLevel = JlptLevel.N5,
            examples = listOf(
                KanjiCompound("月曜日", "げつようび (getsuyoubi)", "Hari Senin"),
                KanjiCompound("一月", "いちがつ (ichigatsu)", "Bulan Januari"),
                KanjiCompound("今月", "こんげつ (kongetsu)", "Bulan ini")
            ),
            exampleSentenceJp = "今夜は月がとても明るいです。",
            exampleSentenceFurigana = "こんやはつきがとてもあかるいです。",
            exampleSentenceId = "Malam ini bulan sangat terang."
        ),
        KanjiItem(
            id = "k3",
            character = "木",
            meaningId = "Pohon, Kayu, Hari Kamis",
            onyomi = "ボク, モク",
            kunyomi = "き, こ-",
            strokeCount = 4,
            jlptLevel = JlptLevel.N5,
            examples = listOf(
                KanjiCompound("木曜日", "もくようび (mokuyoubi)", "Hari Kamis"),
                KanjiCompound("大木", "たいぼく (taiboku)", "Pohon besar")
            ),
            exampleSentenceJp = "庭に桜の木があります。",
            exampleSentenceFurigana = "にわにさくらのきがあります。",
            exampleSentenceId = "Di halaman terdapat pohon sakura."
        ),
        KanjiItem(
            id = "k4",
            character = "水",
            meaningId = "Air, Hari Rabu",
            onyomi = "スイ",
            kunyomi = "みず",
            strokeCount = 4,
            jlptLevel = JlptLevel.N5,
            examples = listOf(
                KanjiCompound("水曜日", "すいようび (suiyoubi)", "Hari Rabu"),
                KanjiCompound("水泳", "すいえい (suiei)", "Berenang"),
                KanjiCompound("飲み水", "のみみず (nomimizu)", "Air minum")
            ),
            exampleSentenceJp = "冷たい水を一杯飲みました。",
            exampleSentenceFurigana = "つめたいみずをいっぱいのみました。",
            exampleSentenceId = "Saya minum segelas air dingin."
        ),
        KanjiItem(
            id = "k5",
            character = "火",
            meaningId = "Api, Hari Selasa",
            onyomi = "カ",
            kunyomi = "ひ",
            strokeCount = 4,
            jlptLevel = JlptLevel.N5,
            examples = listOf(
                KanjiCompound("火曜日", "かようび (kayoubi)", "Hari Selasa"),
                KanjiCompound("花火", "はなび (hanabi)", "Kembang api"),
                KanjiCompound("火事", "かじ (kaji)", "Kebakaran")
            ),
            exampleSentenceJp = "夏に花火を見に行きました。",
            exampleSentenceFurigana = "なつにはなびをみにいきました。",
            exampleSentenceId = "Di musim panas saya pergi menonton kembang api."
        ),
        KanjiItem(
            id = "k6",
            character = "人",
            meaningId = "Orang, Manusia",
            onyomi = "ジン, ニン",
            kunyomi = "ひと",
            strokeCount = 2,
            jlptLevel = JlptLevel.N5,
            examples = listOf(
                KanjiCompound("日本人", "にほんじん (nihonjin)", "Orang Jepang"),
                KanjiCompound("三人", "さんにん (sannin)", "Tiga orang"),
                KanjiCompound("大人", "おとな (otona)", "Orang dewasa")
            ),
            exampleSentenceJp = "あの人は私の友達です。",
            exampleSentenceFurigana = "あのひとはわたしのともだちです。",
            exampleSentenceId = "Orang itu adalah teman saya."
        ),
        KanjiItem(
            id = "k7",
            character = "本",
            meaningId = "Buku, Asal, Pokok",
            onyomi = "ホン",
            kunyomi = "もと",
            strokeCount = 5,
            jlptLevel = JlptLevel.N5,
            examples = listOf(
                KanjiCompound("本屋", "ほんや (hon'ya)", "Toko buku"),
                KanjiCompound("基本", "きほん (kihon)", "Dasar / Fondasi")
            ),
            exampleSentenceJp = "図書館で面白い本を借りました。",
            exampleSentenceFurigana = "としょかんでおもしろいほんをかりました。",
            exampleSentenceId = "Saya meminjam buku menarik di perpustakaan."
        ),
        KanjiItem(
            id = "k8",
            character = "山",
            meaningId = "Gunung",
            onyomi = "サン",
            kunyomi = "やま",
            strokeCount = 3,
            jlptLevel = JlptLevel.N5,
            examples = listOf(
                KanjiCompound("富士山", "ふじさん (fujisan)", "Gunung Fuji"),
                KanjiCompound("山登り", "やまのぼり (yamanobori)", "Mendaki gunung")
            ),
            exampleSentenceJp = "週末に山へ登りました。",
            exampleSentenceFurigana = "しゅうまつにやまへのぼりました。",
            exampleSentenceId = "Di akhir pekan saya mendaki gunung."
        ),
        KanjiItem(
            id = "k9",
            character = "食",
            meaningId = "Makan, Makanan",
            onyomi = "ショク",
            kunyomi = "た(べる), く(う)",
            strokeCount = 9,
            jlptLevel = JlptLevel.N5,
            examples = listOf(
                KanjiCompound("食事", "しょくじ (shokuji)", "Makan bersama"),
                KanjiCompound("食堂", "しょくどう (shokudou)", "Kantin / Ruang makan"),
                KanjiCompound("食べ物", "たべもの (tabemono)", "Makanan")
            ),
            exampleSentenceJp = "一緒に日本料理を食べましょう。",
            exampleSentenceFurigana = "いっしょににほんりょうりをたべましょう。",
            exampleSentenceId = "Mari makan masakan Jepang bersama-sama."
        ),
        KanjiItem(
            id = "k10",
            character = "見",
            meaningId = "Melihat, Memandang",
            onyomi = "ケン",
            kunyomi = "み(る), み(せる)",
            strokeCount = 7,
            jlptLevel = JlptLevel.N5,
            examples = listOf(
                KanjiCompound("見学", "けんがく (kengaku)", "Kunjungan studi"),
                KanjiCompound("意見", "いけん (iken)", "Pendapat")
            ),
            exampleSentenceJp = "京都で古いお寺を見ました。",
            exampleSentenceFurigana = "きょうとでふるいおてらをみました。",
            exampleSentenceId = "Saya melihat kuil kuno di Kyoto."
        )
    )

    // --- GRAMMAR DATA ---
    val grammarList: List<GrammarItem> = listOf(
        GrammarItem(
            id = "g1",
            pattern = "A は B です / ではありません",
            title = "Pola Kalimat Dasar Positif & Negatif Sopan",
            explanationId = "Dalam bahasa Jepang, は (dibaca 'wa') berfungsi sebagai partikel penanda topik utama yang sedang dibicarakan. 'です' (desu) adalah kopula sopan untuk menyatakan 'adalah/merupakan', sedangkan bentuk negatifnya adalah 'ではありません' (dewa arimasen) atau lebih kasual 'じゃありません' (ja arimasen).",
            usageWhen = "Digunakan saat memperkenalkan diri, menyatakan identitas, pekerjaan, kebangsaan, atau sifat suatu benda secara sopan.",
            commonMistakesId = "✕ Jangan salah baca partikel 'は' menjadi 'ha'. Jika berfungsi sebagai partikel penanda topik, selalu dibaca 'wa'.\n✕ Jangan tambahkan 'です' setelah kata kerja bentuk 'ます' (misal: ✕ 食べますです).",
            jlptLevel = JlptLevel.N5,
            exampleSentences = listOf(
                GrammarExample("私はインドネシア人です。", "わたしはいんどねしあじんです。", "Saya adalah orang Indonesia."),
                GrammarExample("田中さんは学生ではありません。", "たなかさんはがくせいではありません。", "Pak Tanaka bukan seorang pelajar."),
                GrammarExample("これは私のペンです。", "これはわたしのぺんです。", "Ini adalah pulpen saya.")
            ),
            miniQuizQuestion = "Pilihlah kalimat yang benar untuk 'Saya bukan guru':",
            miniQuizOptions = listOf(
                "私は先生です。",
                "私は先生ではありません。",
                "私は先生を行きます。",
                "私は先生があります。"
            ),
            miniQuizCorrectIndex = 1,
            miniQuizExplanation = "'ではありません' adalah bentuk negatif sopan untuk kata benda."
        ),
        GrammarItem(
            id = "g2",
            pattern = "Partikel を (o) & Kata Kerja",
            title = "Partikel を Sebagai Penanda Objek Langsung",
            explanationId = "Partikel を (ditulis 'wo' dengan huruf kana を namun selalu dilafalkan 'o') diletakkan tepat setelah kata benda untuk menandai bahwa benda tersebut adalah objek penderita yang dikenai aksi oleh kata kerja transitif.",
            usageWhen = "Digunakan pada aktivitas sehari-hari seperti makan makanan, minum minuman, membaca buku, membeli barang, dll.",
            commonMistakesId = "✕ Jangan menggunakan huruf hiragana お untuk partikel penanda objek. Selalu gunakan huruf を.\n✕ Pola urutan kata dalam bahasa Jepang adalah Subjek - Objek - Kata Kerja (Berbeda dari bahasa Indonesia S-P-O).",
            jlptLevel = JlptLevel.N5,
            exampleSentences = listOf(
                GrammarExample("水を飲みます。", "みずをのみます。", "Saya minum air."),
                GrammarExample("本を読みました。", "ほんをよみました。", "Saya sudah membaca buku."),
                GrammarExample("日本語を勉強します。", "にほんごをべんきょうします。", "Saya belajar bahasa Jepang.")
            ),
            miniQuizQuestion = "Isilah bagian kosong: 'パン ( ... ) 食べます。' (Saya makan roti)",
            miniQuizOptions = listOf("は", "を", "に", "で"),
            miniQuizCorrectIndex = 1,
            miniQuizExplanation = "Roti (パン) adalah objek yang dimakan, jadi menggunakan partikel を."
        ),
        GrammarItem(
            id = "g3",
            pattern = "Partikel に (ni) vs で (de)",
            title = "Perbedaan Tempat Keberadaan & Tempat Beraktivitas",
            explanationId = "Banyak pemula bahasa Indonesia bingung menerjemahkan 'di'. Di bahasa Jepang:\n1. 'に' digunakan untuk tempat keberadaan statis (dengan kata kerja あります / います / 住みます).\n2. 'で' digunakan untuk tempat di mana suatu kegiatan/aktivitas berlangsung.",
            usageWhen = "Gunakan 'で' saat ada aksi dinamis (belajar di perpustakaan, makan di restoran). Gunakan 'に' saat menyatakan di mana seseorang tinggal atau berada.",
            commonMistakesId = "✕ Salah: レストランに食べます (Harusnya: レストランで食べます karena makan adalah aksi).\n✕ Salah: 部屋で猫がいます (Harusnya: 部屋に猫がいます karena menyatakan keberadaan).",
            jlptLevel = JlptLevel.N5,
            exampleSentences = listOf(
                GrammarExample("図書館で勉強します。", "としょかんでべんきょうします。", "Belajar di perpustakaan (Aksi -> で)."),
                GrammarExample("部屋にテレビがあります。", "へやにてれびがあります。", "Ada televisi di dalam kamar (Keberadaan -> に)."),
                GrammarExample("東京に住んでいます。", "とうきょうにすんでいます。", "Tinggal di Tokyo (Tempat tinggal -> に).")
            ),
            miniQuizQuestion = "Kalimat yang benar untuk 'Makan siang di kantin':",
            miniQuizOptions = listOf(
                "食堂に昼ご飯を食べます。",
                "食堂で昼ご飯を食べます。",
                "食堂を昼ご飯を食べます。",
                "食堂は昼ご飯を食べます。"
            ),
            miniQuizCorrectIndex = 1,
            miniQuizExplanation = "Karena ada aksi makan (食べます), tempat menggunakan partikel で."
        ),
        GrammarItem(
            id = "g4",
            pattern = "あります vs います",
            title = "Menyatakan Keberadaan Benda Mati vs Makhluk Hidup",
            explanationId = "Bahasa Jepang membedakan kata 'ada' menjadi dua jenis:\n1. あります (arimasu): untuk benda mati, tanaman, dan konsep abstrak (meja, buku, bunga, acara).\n2. います (imasu): untuk makhluk hidup yang bisa bergerak sendiri seperti manusia dan hewan.",
            usageWhen = "Saat menyatakan keberadaan sesuatu di suatu tempat (Tempat に Subjek が あります/います).",
            commonMistakesId = "✕ Menggunakan います untuk barang (✕ 車がいます -> ✓ 車があります).\n✕ Menggunakan あります untuk orang (✕ 友達があります -> ✓ 友達がいます).",
            jlptLevel = JlptLevel.N5,
            exampleSentences = listOf(
                GrammarExample("机の上に本があります。", "つくえのうえにほんがあります。", "Ada buku di atas meja."),
                GrammarExample("公園に子供がいます。", "こうえんにこどもがいます。", "Ada anak-anak di taman."),
                GrammarExample("庭に犬がいます。", "にわにいぬがいます。", "Ada anjing di halaman.")
            ),
            miniQuizQuestion = "Isilah bagian yang kosong: '部屋に猫が ( ... )。' (Ada kucing di kamar)",
            miniQuizOptions = listOf("あります", "います", "します", "行きます"),
            miniQuizCorrectIndex = 1,
            miniQuizExplanation = "Kucing adalah makhluk hidup/hewan, sehingga menggunakan います."
        ),
        GrammarItem(
            id = "g5",
            pattern = "Kata Kerja Bentuk 〜たい (tai)",
            title = "Menyatakan Keinginan Sendiri 'Ingin Melakukan'",
            explanationId = "Untuk menyatakan keinginan melakukan sesuatu, ubah kata kerja bentuk 'ます' menjadi '〜たい' (hilangkan ます lalu tambahkan たい). Kalimat ini berubah menjadi kata sifat -i, sehingga bentuk negatifnya adalah '〜たくない' (tidak ingin).",
            usageWhen = "Digunakan untuk mengungkapkan keinginan orang pertama (saya). Objek kalimat bisa memakai partikel を atau が.",
            commonMistakesId = "✕ Pola 〜たい umumnya TIDAK digunakan untuk menanyakan langsung keinginan atasan atau orang yang lebih tua karena terkesan terlalu kasual/kurang sopan.",
            jlptLevel = JlptLevel.N5,
            exampleSentences = listOf(
                GrammarExample("日本へ行きたいです。", "にほんへいきたいです。", "Saya ingin pergi ke Jepang."),
                GrammarExample("寿司を食べたいです。", "すしをたべたいです。", "Saya ingin makan sushi."),
                GrammarExample("今日はどこにも行きたくないです。", "きょうはどこにもいきたくないです。", "Hari ini saya tidak ingin pergi ke mana pun.")
            ),
            miniQuizQuestion = "Bagaimana menyatakan 'Saya ingin minum air dingin'?",
            miniQuizOptions = listOf(
                "冷たい水を飲みます。",
                "冷たい水を飲みたいです。",
                "冷たい水を飲みました。",
                "冷たい水を飲まないです。"
            ),
            miniQuizCorrectIndex = 1,
            miniQuizExplanation = "飲みます diubah menjadi 飲みたいです untuk menyatakan ingin minum."
        ),
        GrammarItem(
            id = "g6",
            pattern = "〜てもいいですか (te mo ii desu ka)",
            title = "Meminta Izin Secara Sopan 'Bolehkah saya...?'",
            explanationId = "Gunakan kata kerja bentuk-te diikuti oleh もいいですか untuk meminta izin melakukan sesuatu kepada lawan bicara.",
            usageWhen = "Sangat berguna dalam kehidupan sehari-hari dan di tempat kerja di Jepang (misal: bolehkah duduk di sini, bolehkah mengambil foto).",
            commonMistakesId = "✕ Ingat rumus perubahan bentuk-te kata kerja golongan 1, 2, dan 3.",
            jlptLevel = JlptLevel.N5,
            exampleSentences = listOf(
                GrammarExample("ここに座ってもいいですか。", "ここにすわってもいいですか。", "Bolehkah saya duduk di sini?"),
                GrammarExample("写真を撮ってもいいですか。", "しゃしんをとってもいいですか。", "Bolehkah saya mengambil foto?"),
                GrammarExample("窓を開けてもいいですか。", "まどをあけてもいいですか。", "Bolehkah saya membuka jendela?")
            ),
            miniQuizQuestion = "Terjemahkan 'Bolehkah saya masuk?':",
            miniQuizOptions = listOf(
                "入ってもいいですか。",
                "入ってはいけません。",
                "入りますか。",
                "入りたいですか。"
            ),
            miniQuizCorrectIndex = 0,
            miniQuizExplanation = "入る (hairu) bentuk-te nya adalah 入って (haitte) + もいいですか."
        ),
        GrammarItem(
            id = "g7",
            pattern = "N4: 〜たことがある (ta koto ga aru)",
            title = "Menyatakan Pengalaman Lampau 'Pernah Melakukan'",
            explanationId = "Bentuk lampau polos (bentuk -ta) ditambah ことがある digunakan untuk menyatakan bahwa subjek memiliki pengalaman pernah melakukan hal tersebut di masa lalu. Bentuk negatifnya adalah ことがない (belum pernah).",
            usageWhen = "Saat bercerita tentang pengalaman hidup, traveling, atau mencoba makanan unik.",
            commonMistakesId = "✕ Jangan gunakan kata kerja bentuk lampau sopan (✕ 食べましたことがある). Selalu gunakan bentuk kamus lampau (✓ 食べたことがある).",
            jlptLevel = JlptLevel.N4,
            exampleSentences = listOf(
                GrammarExample("富士山に登ったことがあります。", "ふじさんにのぼったことがあります。", "Saya pernah mendaki Gunung Fuji."),
                GrammarExample("納豆を食べたことがありますか。", "なっとうをたべたことがありますか。", "Apakah Anda pernah makan natto?"),
                GrammarExample("一度も日本へ行ったことがありません。", "いちどもにほんへいったことがありません。", "Saya belum pernah sekalipun pergi ke Jepang.")
            ),
            miniQuizQuestion = "Pilihlah kalimat yang berarti 'Saya pernah melihat anime itu':",
            miniQuizOptions = listOf(
                "そのアニメを見たことがあります。",
                "そのアニメを見ますことがあります。",
                "そのアニメを見たいです。",
                "そのアニメを見ましたです。"
            ),
            miniQuizCorrectIndex = 0,
            miniQuizExplanation = "Kata kerja bentuk lampau polos 見た + ことがあります."
        )
    )

    // --- DAILY CONVERSATION DATA ---
    val conversationList: List<ConversationItem> = listOf(
        ConversationItem(
            id = "c1",
            category = "Perkenalan Diri",
            title = "Salam Kenal di Kantor Baru",
            descriptionId = "Percakapan formal saat pertama kali memperkenalkan diri di depan atasan dan rekan kantor di Jepang.",
            culturalNote = "Di Jepang, saat membungkuk (ojigi), jaga punggung tetap lurus dan ucapkan 'Yoroshiku onegaishimasu' sebagai tanda mengharapkan hubungan kerja sama yang baik.",
            dialogueLines = listOf(
                DialogueLine("Riki (Anda)", true, "初めまして。リキと申します。インドネシアから参りました。", "はじめまして。りきともうします。いんどねしあからまいりました。", "Hajimemashite. Riki to moushimasu. Indoneshia kara mairimashita.", "Salam kenal. Nama saya Riki. Saya datang dari Indonesia."),
                DialogueLine("Yamada (Manajer)", false, "山田です。ようこそ日本へ！日本語がお上手ですね。", "やまだです。ようこそにほんへ！にほんごがおじょうずですね。", "Yamada desu. Youkoso Nihon e! Nihongo ga ojouzu desu ne.", "Saya Yamada. Selamat datang di Jepang! Bahasa Jepang Anda bagus sekali ya."),
                DialogueLine("Riki (Anda)", true, "いいえ、まだまだです。一生懸命頑張りますので、よろしくお願いします。", "いいえ、まだまだです。いっしょうけんめいがんばりますので、よろしくおねがいします。", "Iie, madamada desu. Isshoukenmei gambarimasu node, yoroshiku onegaishimasu.", "Tidak, saya masih banyak belajar. Saya akan berusaha sebaik mungkin, mohon bimbingannya."),
                DialogueLine("Yamada (Manajer)", false, "こちらこそ、よろしくお願いします。困ったことがあったら何でも聞いてくださいね。", "こちらこそ、よろしくおねがいします。こまったことがあったらなんでもきいてくださいね。", "Kochirakoso, yoroshiku onegaishimasu. Komatta koto ga attara nandemo kiite kudasai ne.", "Sama-sama, mohon kerja samanya juga. Kalau ada kesulitan, jangan ragu bertanya ya.")
            )
        ),
        ConversationItem(
            id = "c2",
            category = "Di Konbini",
            title = "Membeli Makanan di Minimarket Jepang",
            descriptionId = "Situasi paling sering dihadapi saat mampir ke 7-Eleven, Lawson, atau FamilyMart untuk membeli bento.",
            culturalNote = "Kasir konbini di Jepang selalu bertanya apakah bento ingin dihangatkan (atamemasu ka) dan apakah perlu kantong belanja (fukuro).",
            dialogueLines = listOf(
                DialogueLine("Kasir", false, "いらっしゃいませ！温めますか。", "いらっしゃいませ！あたためますか。", "Irasshaimase! Atatamemasu ka.", "Selamat datang! Apakah mau dihangatkan?"),
                DialogueLine("Pelanggan (Anda)", true, "はい、お願いします。", "はい、おねがいします。", "Hai, onegaishimasu.", "Iya, tolong dihangatkan."),
                DialogueLine("Kasir", false, "お箸はお付けしますか。", "おはしはおつけしますか。", "Ohashi wa otsuke shimasu ka.", "Apakah memerlukan sumpit?"),
                DialogueLine("Pelanggan (Anda)", true, "はい、一善お願いします。あと、レジ袋も一枚ください。", "はい、いちぜんおねがいします。あと、れじぶくろもいちまいください。", "Hai, ichizen onegaishimasu. Ato, rejibukuro mo ichimai kudasai.", "Iya, tolong satu pasang. Lalu tolong tambahkan satu kantong belanja."),
                DialogueLine("Kasir", false, "かしこまりました。合計で六百五十円になります。", "かしこまりました。ごうけいでろっぴゃくごじゅうえんになります。", "Kashikomarimashita. Goukei de roppyaku gojuu-en ni narimasu.", "Baik dimengerti. Total semuanya menjadi 650 yen.")
            )
        ),
        ConversationItem(
            id = "c3",
            category = "Memesan Makanan",
            title = "Memesan Ramen di Restoran",
            descriptionId = "Cara memesan makanan, meminta rekomendasi menu, dan meminta bon pembayaran di restoran Jepang.",
            culturalNote = "Di Jepang, Anda tidak perlu memberi tip (no tipping culture). Cukup ucapkan 'Gochisousama deshita' saat keluar restoran.",
            dialogueLines = listOf(
                DialogueLine("Pelayan", false, "いらっしゃいませ、何名様ですか。", "いらっしゃいませ、なんめいさまですか。", "Irasshaimase, nanmei-sama desu ka.", "Selamat datang, untuk berapa orang?"),
                DialogueLine("Pelanggan (Anda)", true, "一人です。おすすめは何ですか。", "ひとりです。おすすめはなんですか。", "Hitori desu. Osusume wa nan desu ka.", "Satu orang. Menu yang direkomendasikan apa ya?"),
                DialogueLine("Pelayan", false, "特製醤油ラーメンが一番人気です。", "とくせいしょうゆらーめんがいちばんにんきです。", "Tokusei shouyu raamen ga ichiban ninki desu.", "Ramen kecap shoyu spesial adalah yang paling populer."),
                DialogueLine("Pelanggan (Anda)", true, "じゃ、それを一つください。あと、お水もお願いします。", "じゃ、それをひとつください。あと、おみずもおねがいします。", "Ja, sore o hitotsu kudasai. Ato, omizu mo onegaishimasu.", "Kalau begitu, tolong satu porsi itu. Dan tolong minta air putih juga.")
            )
        ),
        ConversationItem(
            id = "c4",
            category = "Menanyakan Arah",
            title = "Tanya Arah Jalan ke Stasiun",
            descriptionId = "Menanyakan lokasi stasiun terdekat kepada pejalan kaki dengan sopan.",
            culturalNote = "Gunakan 'Sumimasen' sebelum bertanya kepada orang asing di jalan agar terdengar ramah dan santun.",
            dialogueLines = listOf(
                DialogueLine("Anda", true, "すみません、新宿駅はどちらですか。", "すみません、しんじゅくえきはどちらですか。", "Sumimasen, Shinjuku-eki wa dochira desu ka.", "Permisi, stasiun Shinjuku ke arah mana ya?"),
                DialogueLine("Pejalan Kaki", false, "あそこに見える交差点を右に曲がってください。", "あそこにみえるこうさてんをみぎにまがってください。", "Asoko ni mieru kousaten o migi ni magatte kudasai.", "Tolong belok ke kanan di persimpangan yang terlihat di sana."),
                DialogueLine("Anda", true, "右ですね。ここから歩いて何分くらいですか。", "みぎですね。ここからあるいてなんぷんくらいですか。", "Migi desu ne. Koko kara aruite nanpun kurai desu ka.", "Ke kanan ya. Dari sini jalan kaki kira-kira berapa menit?"),
                DialogueLine("Pejalan Kaki", false, "五分くらいで着きますよ。", "ごふんくらいでつきますよ。", "Gofun kurai de tsukimasu yo.", "Kira-kira lima menit sudah sampai kok."),
                DialogueLine("Anda", true, "分かりました。どうもありがとうございます！", "わかりました。どうもありがとうございます！", "Wakarimashita. Doumo arigatou gozaimasu!", "Saya mengerti. Terima kasih banyak!")
            )
        )
    )

    // --- LISTENING EXERCISES ---
    val listeningList: List<ListeningExercise> = listOf(
        ListeningExercise(
            id = "l1",
            title = "Latihan 1: Salam & Sapaan",
            jlptLevel = JlptLevel.BEGINNER,
            audioPrompt = "おはようございます。今日も一日頑張りましょう。",
            furigana = "おはようございます。きょうもいちにちがんばりましょう。",
            romaji = "Ohayou gozaimasu. Kyou mo ichinichi gambarimashou.",
            indonesianTranslation = "Selamat pagi. Hari ini mari kita semangat menjalani hari.",
            question = "Kapan salam ini biasanya diucapkan?",
            options = listOf("Malam hari sebelum tidur", "Pagi hari saat bertemu", "Siang hari saat makan", "Saat berpamitan"),
            correctIndex = 1,
            explanation = "'おはようございます' adalah ucapan selamat pagi sopan dalam bahasa Jepang."
        ),
        ListeningExercise(
            id = "l2",
            title = "Latihan 2: Jam & Waktu Janji",
            jlptLevel = JlptLevel.N5,
            audioPrompt = "明日の会議は午後二時半からです。遅れないでください。",
            furigana = "あしたのかいぎはごごにじはんからです。お专业れないでください。",
            romaji = "Ashita no kaigi wa gogo nijihan kara desu. Okurenaide kudasai.",
            indonesianTranslation = "Rapat besok dimulai dari jam 2:30 siang. Tolong jangan terlambat.",
            question = "Pukul berapa rapat besok akan dimulai?",
            options = listOf("12:00 siang", "01:30 siang", "02:30 siang", "03:00 sore"),
            correctIndex = 2,
            explanation = "二時半 (nijihan) berarti jam 2 lewat 30 menit (jam setengah tiga)."
        ),
        ListeningExercise(
            id = "l3",
            title = "Latihan 3: Instruksi Berpergian",
            jlptLevel = JlptLevel.N5,
            audioPrompt = "駅に着いたら、南口を出てすぐのカフェで待っています。",
            furigana = "えきについたら、みなみぐちをでてすぐのかふぇでまっています。",
            romaji = "Eki ni tsuitara, minamiguchi o dete sugu no kafe de matte imasu.",
            indonesianTranslation = "Begitu sampai stasiun, saya menunggu di kafe tepat setelah keluar pintu selatan.",
            question = "Di pintu keluar mana orang tersebut menunggu?",
            options = listOf("Pintu Utara (Kita-guchi)", "Pintu Selatan (Minami-guchi)", "Pintu Timur (Higashi-guchi)", "Pintu Barat (Nishi-guchi)"),
            correctIndex = 1,
            explanation = "南口 (minamiguchi) berarti pintu gerbang arah selatan."
        )
    )

    // --- QUIZ QUESTIONS ---
    fun generateQuizQuestions(level: JlptLevel = JlptLevel.N5): List<QuizQuestion> = listOf(
        QuizQuestion(
            id = "q1",
            type = QuizType.JP_TO_ID,
            prompt = "猫",
            subPrompt = "Pilihlah arti kata dalam bahasa Indonesia",
            audioText = "ねこ",
            options = listOf("Kucing", "Anjing", "Burung", "Ikan"),
            correctIndex = 0,
            explanation = "猫 (ねこ / neko) berarti kucing."
        ),
        QuizQuestion(
            id = "q2",
            type = QuizType.ID_TO_JP,
            prompt = "Terima kasih banyak (sopan)",
            subPrompt = "Pilihlah ungkapan bahasa Jepang yang tepat",
            audioText = "ありがとうございます",
            options = listOf("すみません", "ありがとうございます", "いただきます", "さようなら"),
            correctIndex = 1,
            explanation = "'ありがとうございます' adalah ucapan terima kasih standar yang sopan."
        ),
        QuizQuestion(
            id = "q3",
            type = QuizType.LISTENING,
            prompt = "Dengarkan audio dan pilihlah arti yang diucapkan:",
            audioText = "水を一杯ください",
            options = listOf(
                "Tolong satu cangkir kopi",
                "Tolong minta satu gelas air putih",
                "Tolong bersihkan meja",
                "Tolong berikan buku itu"
            ),
            correctIndex = 1,
            explanation = "水 (mizu) = air, 一杯 (ippai) = satu gelas, ください (kudasai) = tolong berikan."
        ),
        QuizQuestion(
            id = "q4",
            type = QuizType.KANJI,
            prompt = "Apa bacaan onyomi / kunyomi dari kanji '日'?",
            subPrompt = "日 (Matahari, hari)",
            audioText = "にち",
            options = listOf("つき / ゲツ", "ひ / ニチ", "みず / スイ", "やま / サン"),
            correctIndex = 1,
            explanation = "Kanji 日 dibaca 'ひ' (kunyomi) atau 'ニチ / ジツ' (onyomi)."
        ),
        QuizQuestion(
            id = "q5",
            type = QuizType.KANA,
            prompt = "Pilihlah romaji yang tepat untuk huruf Katakana ini: 'テ'",
            subPrompt = "Katakana",
            audioText = "て",
            options = listOf("ta", "chi", "te", "to"),
            correctIndex = 2,
            explanation = "'テ' adalah huruf Katakana untuk bunyi 'te'."
        ),
        QuizQuestion(
            id = "q6",
            type = QuizType.GRAMMAR,
            prompt = "Isilah bagian yang kosong:\n'明日東京 ( ... ) 行きます。'",
            subPrompt = "Partikel yang menyatakan arah/tujuan",
            audioText = "あしたとうきょうへいきます",
            options = listOf("を", "へ / に", "で", "と"),
            correctIndex = 1,
            explanation = "Partikel へ (he dibaca e) atau に digunakan untuk menandai tempat tujuan perpindahan."
        )
    )

    // --- DAILY MISSIONS ---
    fun getDefaultDailyMissions(): List<DailyMission> = listOf(
        DailyMission("m1", "Review Harian", "Selesaikan minimal 5 kartu di antrean review", 5, 2, 30, false),
        DailyMission("m2", "Pelajari Kosakata Baru", "Buka dan pelajari 5 kosakata baru", 5, 5, 40, true),
        DailyMission("m3", "Latihan Menyimak", "Dengarkan dan selesaikan 1 latihan audio", 1, 0, 25, false),
        DailyMission("m4", "Kuis Pemahaman", "Dapatkan skor 80%+ pada sesi kuis hari ini", 1, 1, 50, true)
    )
}
