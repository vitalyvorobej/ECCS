PGDMP     %    &                v            mybase-test    10.1    10.1 ?    <           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            =           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            >           1262    17277    mybase-test    DATABASE     �   CREATE DATABASE "mybase-test" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';
    DROP DATABASE "mybase-test";
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            ?           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12924    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            @           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    17329    bracelet    TABLE     �   CREATE TABLE bracelet (
    id integer NOT NULL,
    uuid character varying(25) NOT NULL,
    free boolean NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);
    DROP TABLE public.bracelet;
       public         postgres    false    3            �            1259    17327    bracelet_id_seq    SEQUENCE     �   CREATE SEQUENCE bracelet_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.bracelet_id_seq;
       public       postgres    false    3    207            A           0    0    bracelet_id_seq    SEQUENCE OWNED BY     5   ALTER SEQUENCE bracelet_id_seq OWNED BY bracelet.id;
            public       postgres    false    206            �            1259    17280    card    TABLE       CREATE TABLE card (
    id integer NOT NULL,
    date_registration timestamp without time zone NOT NULL,
    client_id integer NOT NULL,
    active boolean NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);
    DROP TABLE public.card;
       public         postgres    false    3            �            1259    17278    card_id_seq    SEQUENCE     |   CREATE SEQUENCE card_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.card_id_seq;
       public       postgres    false    197    3            B           0    0    card_id_seq    SEQUENCE OWNED BY     -   ALTER SEQUENCE card_id_seq OWNED BY card.id;
            public       postgres    false    196            �            1259    17288    client    TABLE     R  CREATE TABLE client (
    id integer NOT NULL,
    first_name character varying(30) NOT NULL,
    last_name character varying(50) NOT NULL,
    birthday_date timestamp without time zone NOT NULL,
    phone_number character varying(25),
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);
    DROP TABLE public.client;
       public         postgres    false    3            �            1259    17286    client_id_seq    SEQUENCE     ~   CREATE SEQUENCE client_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.client_id_seq;
       public       postgres    false    199    3            C           0    0    client_id_seq    SEQUENCE OWNED BY     1   ALTER SEQUENCE client_id_seq OWNED BY client.id;
            public       postgres    false    198            �            1259    17296    order_object    TABLE     �  CREATE TABLE order_object (
    id integer NOT NULL,
    start_time timestamp without time zone NOT NULL,
    end_time timestamp without time zone NOT NULL,
    card_id integer NOT NULL,
    ticket_type_id integer NOT NULL,
    ticket_price numeric NOT NULL,
    bracelet_id integer NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);
     DROP TABLE public.order_object;
       public         postgres    false    3            �            1259    17294    order_object_id_seq    SEQUENCE     �   CREATE SEQUENCE order_object_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.order_object_id_seq;
       public       postgres    false    3    201            D           0    0    order_object_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE order_object_id_seq OWNED BY order_object.id;
            public       postgres    false    200            �            1259    17318    payment    TABLE       CREATE TABLE payment (
    id integer NOT NULL,
    amount numeric NOT NULL,
    order_id integer NOT NULL,
    created timestamp without time zone NOT NULL,
    payment_type character varying(15) NOT NULL,
    updated timestamp without time zone NOT NULL
);
    DROP TABLE public.payment;
       public         postgres    false    3            �            1259    17316    payment_id_seq    SEQUENCE        CREATE SEQUENCE payment_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.payment_id_seq;
       public       postgres    false    3    205            E           0    0    payment_id_seq    SEQUENCE OWNED BY     3   ALTER SEQUENCE payment_id_seq OWNED BY payment.id;
            public       postgres    false    204            �            1259    17307    ticket_type    TABLE     �   CREATE TABLE ticket_type (
    id integer NOT NULL,
    name character varying(30) NOT NULL,
    price numeric NOT NULL,
    deleted boolean NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);
    DROP TABLE public.ticket_type;
       public         postgres    false    3            �            1259    17305    ticket_type_id_seq    SEQUENCE     �   CREATE SEQUENCE ticket_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.ticket_type_id_seq;
       public       postgres    false    203    3            F           0    0    ticket_type_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE ticket_type_id_seq OWNED BY ticket_type.id;
            public       postgres    false    202            �            1259    17339    user_account    TABLE       CREATE TABLE user_account (
    id integer NOT NULL,
    email character varying(40) NOT NULL,
    password character varying(30) NOT NULL,
    role character varying(15) NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);
     DROP TABLE public.user_account;
       public         postgres    false    3            �            1259    17337    user_account_id_seq    SEQUENCE     �   CREATE SEQUENCE user_account_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.user_account_id_seq;
       public       postgres    false    3    209            G           0    0    user_account_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE user_account_id_seq OWNED BY user_account.id;
            public       postgres    false    208            �
           2604    17332    bracelet id    DEFAULT     \   ALTER TABLE ONLY bracelet ALTER COLUMN id SET DEFAULT nextval('bracelet_id_seq'::regclass);
 :   ALTER TABLE public.bracelet ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    207    206    207            �
           2604    17283    card id    DEFAULT     T   ALTER TABLE ONLY card ALTER COLUMN id SET DEFAULT nextval('card_id_seq'::regclass);
 6   ALTER TABLE public.card ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    196    197    197            �
           2604    17291 	   client id    DEFAULT     X   ALTER TABLE ONLY client ALTER COLUMN id SET DEFAULT nextval('client_id_seq'::regclass);
 8   ALTER TABLE public.client ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    199    198    199            �
           2604    17299    order_object id    DEFAULT     d   ALTER TABLE ONLY order_object ALTER COLUMN id SET DEFAULT nextval('order_object_id_seq'::regclass);
 >   ALTER TABLE public.order_object ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    201    200    201            �
           2604    17321 
   payment id    DEFAULT     Z   ALTER TABLE ONLY payment ALTER COLUMN id SET DEFAULT nextval('payment_id_seq'::regclass);
 9   ALTER TABLE public.payment ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    205    204    205            �
           2604    17310    ticket_type id    DEFAULT     b   ALTER TABLE ONLY ticket_type ALTER COLUMN id SET DEFAULT nextval('ticket_type_id_seq'::regclass);
 =   ALTER TABLE public.ticket_type ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    202    203    203            �
           2604    17342    user_account id    DEFAULT     d   ALTER TABLE ONLY user_account ALTER COLUMN id SET DEFAULT nextval('user_account_id_seq'::regclass);
 >   ALTER TABLE public.user_account ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    209    208    209            7          0    17329    bracelet 
   TABLE DATA                     public       postgres    false    207   �B       -          0    17280    card 
   TABLE DATA                     public       postgres    false    197   UC       /          0    17288    client 
   TABLE DATA                     public       postgres    false    199   ED       1          0    17296    order_object 
   TABLE DATA                     public       postgres    false    201   �E       5          0    17318    payment 
   TABLE DATA                     public       postgres    false    205   �F       3          0    17307    ticket_type 
   TABLE DATA                     public       postgres    false    203   �G       9          0    17339    user_account 
   TABLE DATA                     public       postgres    false    209   xH       H           0    0    bracelet_id_seq    SEQUENCE SET     8   SELECT pg_catalog.setval('bracelet_id_seq', 584, true);
            public       postgres    false    206            I           0    0    card_id_seq    SEQUENCE SET     4   SELECT pg_catalog.setval('card_id_seq', 594, true);
            public       postgres    false    196            J           0    0    client_id_seq    SEQUENCE SET     6   SELECT pg_catalog.setval('client_id_seq', 776, true);
            public       postgres    false    198            K           0    0    order_object_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('order_object_id_seq', 395, true);
            public       postgres    false    200            L           0    0    payment_id_seq    SEQUENCE SET     7   SELECT pg_catalog.setval('payment_id_seq', 201, true);
            public       postgres    false    204            M           0    0    ticket_type_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('ticket_type_id_seq', 580, true);
            public       postgres    false    202            N           0    0    user_account_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('user_account_id_seq', 228, true);
            public       postgres    false    208            �
           2606    17334    bracelet bracelet_pk 
   CONSTRAINT     K   ALTER TABLE ONLY bracelet
    ADD CONSTRAINT bracelet_pk PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.bracelet DROP CONSTRAINT bracelet_pk;
       public         postgres    false    207            �
           2606    17336    bracelet bracelet_uuid_key 
   CONSTRAINT     N   ALTER TABLE ONLY bracelet
    ADD CONSTRAINT bracelet_uuid_key UNIQUE (uuid);
 D   ALTER TABLE ONLY public.bracelet DROP CONSTRAINT bracelet_uuid_key;
       public         postgres    false    207            �
           2606    17285    card card_pk 
   CONSTRAINT     C   ALTER TABLE ONLY card
    ADD CONSTRAINT card_pk PRIMARY KEY (id);
 6   ALTER TABLE ONLY public.card DROP CONSTRAINT card_pk;
       public         postgres    false    197            �
           2606    17293    client client_pk 
   CONSTRAINT     G   ALTER TABLE ONLY client
    ADD CONSTRAINT client_pk PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.client DROP CONSTRAINT client_pk;
       public         postgres    false    199            �
           2606    17304    order_object order_object_pk 
   CONSTRAINT     S   ALTER TABLE ONLY order_object
    ADD CONSTRAINT order_object_pk PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.order_object DROP CONSTRAINT order_object_pk;
       public         postgres    false    201            �
           2606    17326    payment payment_pk 
   CONSTRAINT     I   ALTER TABLE ONLY payment
    ADD CONSTRAINT payment_pk PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.payment DROP CONSTRAINT payment_pk;
       public         postgres    false    205            �
           2606    17315    ticket_type ticket_type_pk 
   CONSTRAINT     Q   ALTER TABLE ONLY ticket_type
    ADD CONSTRAINT ticket_type_pk PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.ticket_type DROP CONSTRAINT ticket_type_pk;
       public         postgres    false    203            �
           2606    17346 #   user_account user_account_email_key 
   CONSTRAINT     X   ALTER TABLE ONLY user_account
    ADD CONSTRAINT user_account_email_key UNIQUE (email);
 M   ALTER TABLE ONLY public.user_account DROP CONSTRAINT user_account_email_key;
       public         postgres    false    209            �
           2606    17344    user_account user_account_pk 
   CONSTRAINT     S   ALTER TABLE ONLY user_account
    ADD CONSTRAINT user_account_pk PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.user_account DROP CONSTRAINT user_account_pk;
       public         postgres    false    209            �
           2606    17347    card card_fk0    FK CONSTRAINT     a   ALTER TABLE ONLY card
    ADD CONSTRAINT card_fk0 FOREIGN KEY (client_id) REFERENCES client(id);
 7   ALTER TABLE ONLY public.card DROP CONSTRAINT card_fk0;
       public       postgres    false    2719    199    197            �
           2606    17352    order_object order_object_fk0    FK CONSTRAINT     m   ALTER TABLE ONLY order_object
    ADD CONSTRAINT order_object_fk0 FOREIGN KEY (card_id) REFERENCES card(id);
 G   ALTER TABLE ONLY public.order_object DROP CONSTRAINT order_object_fk0;
       public       postgres    false    201    2717    197            �
           2606    17357    order_object order_object_fk1    FK CONSTRAINT     {   ALTER TABLE ONLY order_object
    ADD CONSTRAINT order_object_fk1 FOREIGN KEY (ticket_type_id) REFERENCES ticket_type(id);
 G   ALTER TABLE ONLY public.order_object DROP CONSTRAINT order_object_fk1;
       public       postgres    false    2723    201    203            �
           2606    17362    order_object order_object_fk2    FK CONSTRAINT     u   ALTER TABLE ONLY order_object
    ADD CONSTRAINT order_object_fk2 FOREIGN KEY (bracelet_id) REFERENCES bracelet(id);
 G   ALTER TABLE ONLY public.order_object DROP CONSTRAINT order_object_fk2;
       public       postgres    false    201    207    2727            �
           2606    17367    payment payment_fk0    FK CONSTRAINT     l   ALTER TABLE ONLY payment
    ADD CONSTRAINT payment_fk0 FOREIGN KEY (order_id) REFERENCES order_object(id);
 =   ALTER TABLE ONLY public.payment DROP CONSTRAINT payment_fk0;
       public       postgres    false    201    2721    205            7   �   x��α
�0�ݧ�-
���h�B�Pmw�)���o����.�q����U]�(��
ϱ���'��}�l�k�:�n�������%���r/j�Q�'����UĒ���1��)�2$�(8y��n\�)�q��!a
b�C�wKt�#6��PT�l۱D�8�)@�9E�l:��8<�}t��      -   �   x��һ
�0�Oq�*ĐKӤurp(���UB%P����[�C�[!��O�����z�b{�A�]3[!��7gn����� (kk�����mڑ3�^;x=��9�W��� 3�1#T-H� �/F e�����oH�"g*�V2�G��2*&2��@p��3�f
��]4!��k������<��hB4���!����� �LNz�����9��
���5G��Z�a      /   �  x��ӽN�0 �O᭭H��N�&��P�ha��&���P�t`+,0 b�
"R�y�����2F���Ζ�˝�f�����	��}/�P�w%t�WQ;p�����g����\�]'��wx�`4�x����Q8<&i��Ώ��jMT1"�2�K>�b̧|VN"�|"n`�<��I���*�U�T�N�$|@�h�b�$�mf��@X�	���0j楪��zA\���Ps N����o����|�d�-K�˘Ȗ��Ә����*P�m��fP/v��������O�8KL)%:��� 6�
6sSj���bژ/S�S����^�f��m�9�BM���ܦ
T���M��OR�+8?y]�a�W�~��P��q}-��8kR[7@��R .�� p�~�      1     x���=O�0�=�ⶴR��A�tb�	��(�=
����8`��DH�"Y�{oy��q�p��p����ƶ��Q6����uֵ�5�7:�Yݎ��Ջ���`�y���gg�)s��)k:g|�1��������&1���r$9r ��~i��ZH�KE2���sHnrR���Z`Q��mwI�=]�g��2�qBd��Rʩ�/Z��-��XO爔~7��J�|���z6G�U!X5��J�b�c=��&X �3�������U�      5   �   x���v
Q���W(H��M�+Q��L�QH��/�+�Q�/JI-��$�&��Pu�%��:
�) aM�0G�P�`#cKCu#C]3]sCS+cC+#C=sK3u�ԅY6\l��ta����.lPǣ\Ӛ˓N5�:����zFFF`�ν����;��{���V�N�*:�� �i�      3   �   x���v
Q���W(�L�N-�/�,HU��L�Q�K�M�Q((�LR)�9�%�@���D0�� ��Ts�	uV�05��QP����֋M/캰��Nu��Ĝb��F��f��
��VF�V�z�f��x�4��<��H�#']�~���>�3w_�&�LC3=#�΄J��� ��e�      9   �   x���v
Q���W(-N-�OLN�/�+Q��L�QH�M���Q(H,..�/
���($�&����) ��B��O�k��������zbJnf�:V�����������������������)>)Mk.Oj���&Cb�ilde`�gnf�O
�L.. ݜ[+     